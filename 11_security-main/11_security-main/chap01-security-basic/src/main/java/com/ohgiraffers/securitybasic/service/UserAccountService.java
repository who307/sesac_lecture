package com.ohgiraffers.securitybasic.service;

import com.ohgiraffers.securitybasic.user.domain.UserAccount;
import com.ohgiraffers.securitybasic.user.domain.UserRole;
import com.ohgiraffers.securitybasic.user.dto.SignupRequest;
import com.ohgiraffers.securitybasic.user.dto.UserResponse;
import com.ohgiraffers.securitybasic.user.repository.UserAccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;


    public UserAccountService(UserAccountRepository userAccountRepository, PasswordEncoder passwordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponse signup(SignupRequest request){
        if(userAccountRepository.existsByUserId(request.userId())) {
            throw new IllegalStateException(("이미 사용 중인 아이디입니다."));
        }
        if(userAccountRepository.existsByEmail(request.email())) {
            throw new IllegalStateException(("이미 사용 중인 이메일입니다."));
        }

        UserAccount user = new UserAccount(
                request.userId(),
                passwordEncoder.encode(request.password()),
                request.userName(),
                request.email(),
                UserRole.USER
        );

        return UserResponse.from(userAccountRepository.save(user));
    }

    public UserResponse findByUserId(String userId) {
        UserAccount user = userAccountRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
        return UserResponse.from(user);
    }
}

