package com.ohgiraffers.securitybasic.auth;

import com.ohgiraffers.securitybasic.user.domain.UserAccount;
import com.ohgiraffers.securitybasic.user.repository.UserAccountRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/* Spring Security가 인증할 아이디를 DB 회원과 연결하는 조회 담당 클래스
* Provider(인증담당자)가 비밀번호를 검사하기 전에 이 클래스에서 회원정보를 받아 간다. */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;

    public CustomUserDetailsService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    /* 인증 요청의 아이디로 회원을 찾고, 시큐리티가 사용할 UserDetails로 바꾼다.
    * 저장된 비밀번호 해시와 역할을 전달하며, 비밀번호 일치 여부는 여기서 판단하지 않는다. */
    @Override
    public UserDetails loadUserByUsername(String username) {
        UserAccount user = userAccountRepository.findByUserId(username)
                .orElseThrow(() -> new UsernameNotFoundException("회원을 찾을 수 없습니다."));

        return User.withUsername(user.getUserId())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .disabled(!user.isEnabled())
                .build();
    }
}
