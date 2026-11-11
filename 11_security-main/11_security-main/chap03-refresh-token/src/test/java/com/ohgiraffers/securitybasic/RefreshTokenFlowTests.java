package com.ohgiraffers.securitybasic;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import com.ohgiraffers.securitybasic.auth.domain.RefreshToken;
import com.ohgiraffers.securitybasic.auth.repository.RefreshTokenRepository;
import com.ohgiraffers.securitybasic.user.domain.UserAccount;
import com.ohgiraffers.securitybasic.user.domain.UserRole;
import com.ohgiraffers.securitybasic.user.repository.UserAccountRepository;
import jakarta.servlet.http.Cookie;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class RefreshTokenFlowTests {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private UserAccountRepository userAccountRepository;
    @Autowired private RefreshTokenRepository refreshTokenRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;

    @Test
    @DisplayName("로그인하면 Access Token과 HttpOnly Refresh Token을 발급한다")
    void loginIssuesTwoTokensTest() throws Exception {
        UserAccount user = saveUser();

        LoginResult login = login(user);

        assertThat(login.accessToken()).isNotBlank();
        assertThat(login.refreshCookie().isHttpOnly()).isTrue();
        assertThat(login.refreshCookie().getValue()).isNotBlank();

        RefreshToken savedToken = refreshTokenRepository.findByUser(user).orElseThrow();
        assertThat(savedToken.getTokenHash())
                .isNotEqualTo(login.refreshCookie().getValue());
    }

    @Test
    @DisplayName("재발급하면 Access Token과 Refresh Token을 모두 교체한다")
    void refreshRotatesTokensTest() throws Exception {
        UserAccount user = saveUser();
        LoginResult login = login(user);

        MvcResult result = mockMvc.perform(post("/api/auth/refresh")
                        .cookie(login.refreshCookie()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").isNotEmpty())
                .andReturn();

        String newAccessToken = objectMapper.readTree(
                result.getResponse().getContentAsString()).get("accessToken").asText();
        Cookie newRefreshCookie = result.getResponse().getCookie("refresh_token");

        assertThat(newAccessToken).isNotEqualTo(login.accessToken());
        assertThat(newRefreshCookie).isNotNull();
        assertThat(newRefreshCookie.getValue())
                .isNotEqualTo(login.refreshCookie().getValue());

        mockMvc.perform(post("/api/auth/refresh")
                        .cookie(login.refreshCookie()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("만료된 Refresh Token으로는 재발급할 수 없다")
    void expiredRefreshTokenTest() throws Exception {
        UserAccount user = saveUser();
        LoginResult login = login(user);
        RefreshToken savedToken = refreshTokenRepository.findByUser(user).orElseThrow();

        jdbcTemplate.update(
                "update tbl_refresh_token set expires_at = ? where token_hash = ?",
                Timestamp.from(Instant.now().minusSeconds(1)),
                savedToken.getTokenHash()
        );
        entityManager.clear();

        mockMvc.perform(post("/api/auth/refresh")
                        .cookie(login.refreshCookie()))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("만료된 Refresh Token입니다."));
    }

    @Test
    @DisplayName("비활성 계정은 유효한 Refresh Token이 있어도 재발급할 수 없다")
    void disabledAccountRefreshTokenTest() throws Exception {
        UserAccount user = saveUser();
        LoginResult login = login(user);

        jdbcTemplate.update(
                "update tbl_user_account set enabled = false where user_id = ?",
                user.getUserId()
        );
        entityManager.clear();

        mockMvc.perform(post("/api/auth/refresh")
                        .cookie(login.refreshCookie()))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("사용할 수 없는 계정입니다."));
    }

    @Test
    @DisplayName("위조된 Refresh Token으로는 재발급할 수 없다")
    void invalidRefreshTokenTest() throws Exception {
        mockMvc.perform(post("/api/auth/refresh")
                        .cookie(new Cookie("refresh_token", "forged-token")))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message")
                        .value("유효하지 않은 Refresh Token입니다."));
    }

    @Test
    @DisplayName("Refresh Token Cookie가 없으면 401을 반환한다")
    void missingRefreshTokenTest() throws Exception {
        mockMvc.perform(post("/api/auth/refresh"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message")
                        .value("Refresh Token이 필요합니다."));
    }

    @Test
    @DisplayName("로그아웃하면 Refresh Token을 폐기하고 Cookie를 만료시킨다")
    void logoutRevokesRefreshTokenTest() throws Exception {
        UserAccount user = saveUser();
        LoginResult login = login(user);

        mockMvc.perform(post("/api/auth/logout")
                        .cookie(login.refreshCookie()))
                .andExpect(status().isNoContent())
                .andExpect(header().string(HttpHeaders.SET_COOKIE,
                        org.hamcrest.Matchers.containsString("Max-Age=0")));

        assertThat(refreshTokenRepository.findByUser(user)).isEmpty();

        mockMvc.perform(post("/api/auth/refresh")
                        .cookie(login.refreshCookie()))
                .andExpect(status().isUnauthorized());
    }

    private UserAccount saveUser() {
        String suffix = UUID.randomUUID().toString().substring(0, 8);
        return userAccountRepository.save(new UserAccount(
                "user-" + suffix,
                passwordEncoder.encode("pass1234"),
                "테스트 사용자",
                suffix + "@test.com",
                UserRole.USER
        ));
    }

    private LoginResult login(UserAccount user) throws Exception {
        MvcResult result = mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "userId": "%s",
                                  "password": "pass1234"
                                }
                                """.formatted(user.getUserId())))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.SET_COOKIE,
                        org.hamcrest.Matchers.containsString("HttpOnly")))
                .andExpect(header().string(HttpHeaders.SET_COOKIE,
                        org.hamcrest.Matchers.containsString("SameSite=Strict")))
                .andReturn();

        JsonNode response = objectMapper.readTree(
                result.getResponse().getContentAsString());
        Cookie refreshCookie = result.getResponse().getCookie("refresh_token");
        assertThat(refreshCookie).isNotNull();

        return new LoginResult(response.get("accessToken").asText(), refreshCookie);
    }

    private record LoginResult(
            String accessToken,
            Cookie refreshCookie
    ) {
    }
}
