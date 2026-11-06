package com.ohgiraffers.securitybasic.auth;

import com.ohgiraffers.securitybasic.user.domain.UserAccount;
import com.ohgiraffers.securitybasic.user.repository.UserAccountRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
* UserDetailsService : 아이디를 받아 사용자 정보를 돌려주는 역할의 인터페이스
* UserDetails : 시큐ㅣ리티가 읽는 사용자 정보 공통 규격
* */
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;

    public CustomUserDetailService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        UserAccount user = userAccountRepository.findByUserId(username)
                .orElseThrow(() - new UsernameNotFoundException("회원을 찾을 수 없습니다."));

        // User 클래스에서 제공하는 메서드를 이용하여 UserDetails를 구현한다
        return User.withUsername(user.getUserId())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .disabled(!user.isEnabled())
                .build();
    }
}
