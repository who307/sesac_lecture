package com.ohgiraffers.securitybasic.auth;

import com.ohgiraffers.securitybasic.auth.dto.LoginRequest;
import com.ohgiraffers.securitybasic.auth.dto.TokenResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    /* 아이디,비밀번호를 인증한 뒤에만 Access Token 응답을 만든다 */
    public TokenResponse login(LoginRequest request) {
        // 아이디와 비밀번호를 Provider에 전달, 인증 실패 시 토큰은 발급되지 않음
        Authentication authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken.unauthenticated(
                        request.userId(), request.password()
                )
        );
        // 검증을 통과한 Authentication의 아이디와 권한으로 Access Token을 만든다.
        String accessToken = jwtService.createAccessToken(authentication);
        return new TokenResponse(
                "Bearer",
                accessToken,
                jwtService.getExpirationSeconds()
        );
    }
}
