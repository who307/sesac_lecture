package com.ohgiraffers.securitybasic;

import com.ohgiraffers.securitybasic.user.domain.UserAccount;
import com.ohgiraffers.securitybasic.user.domain.UserRole;
import com.ohgiraffers.securitybasic.user.repository.UserAccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class SecurityFlowTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원 가입 시 비밀번호를 암호화하고 응답에서는 제외한다")
    void signupTest() throws Exception {
        String suffix = UUID.randomUUID().toString().substring(0, 8);
        String userId = "user-" + suffix;
        String rawPassword = "pass1234";

        mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "userId": "%s",
                                  "password": "%s",
                                  "userName": "홍길동",
                                  "email": "%s@test.com"
                                }
                                """.formatted(userId, rawPassword, suffix)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").value(userId))
                .andExpect(jsonPath("$.password").doesNotExist())
                .andExpect(jsonPath("$.role").value("USER"));

        UserAccount savedUser = userAccountRepository.findByUserId(userId).orElseThrow();
        assertThat(savedUser.getPassword()).isNotEqualTo(rawPassword);
        assertThat(passwordEncoder.matches(rawPassword, savedUser.getPassword())).isTrue();
    }

    @Test
    @DisplayName("인증 정보 없이 보호된 API를 요청하면 401을 반환한다")
    void authenticationRequiredTest() throws Exception {
        mockMvc.perform(get("/api/users/me"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("올바른 아이디와 비밀번호로 자신의 정보를 조회한다")
    void authenticatedUserTest() throws Exception {
        UserAccount user = saveUser(UserRole.USER);

        mockMvc.perform(get("/api/users/me")
                        .with(httpBasic(user.getUserId(), "pass1234")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(user.getUserId()));
    }

    @Test
    @DisplayName("USER 권한으로 관리자 API를 요청하면 403을 반환한다")
    void authorizationDeniedTest() throws Exception {
        UserAccount user = saveUser(UserRole.USER);

        mockMvc.perform(get("/api/admin/dashboard")
                        .with(httpBasic(user.getUserId(), "pass1234")))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("ADMIN 권한으로 관리자 API를 요청하면 접근할 수 있다")
    void adminAuthorizationTest() throws Exception {
        UserAccount admin = saveUser(UserRole.ADMIN);

        mockMvc.perform(get("/api/admin/dashboard")
                        .with(httpBasic(admin.getUserId(), "pass1234")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("관리자 페이지입니다."));
    }

    private UserAccount saveUser(UserRole role) {
        String suffix = UUID.randomUUID().toString().substring(0, 8);
        return userAccountRepository.save(new UserAccount(
                "user-" + suffix,
                passwordEncoder.encode("pass1234"),
                "테스트 사용자",
                suffix + "@test.com",
                role
        ));
    }
}
