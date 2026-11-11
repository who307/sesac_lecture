package com.ohgiraffers.securitybasic.auth;

import com.ohgiraffers.securitybasic.auth.dto.LoginRequest;
import com.ohgiraffers.securitybasic.auth.dto.TokenResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/** 비밀번호 로그인과 Refresh Token 재발급·로그아웃의 업무 흐름을 연결한다. */
@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public AuthService(AuthenticationManager authenticationManager,
                       JwtService jwtService,
                       RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
    }

    /** 비밀번호 인증 후 Access Token과 Refresh Token을 함께 만든다. */
    public AuthTokens login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken.unauthenticated(
                        request.userId(), request.password())
        );

        String accessToken = jwtService.createAccessToken(authentication);
        String refreshToken = refreshTokenService.issue(authentication.getName());

        return new AuthTokens(
                createTokenResponse(accessToken),
                refreshToken
        );
    }

    /** 제출한 Refresh Token을 교체하고 새 Access Token을 발급한다. */
    public AuthTokens refresh(String refreshToken) {
        RefreshTokenService.Rotation rotation = refreshTokenService.rotate(refreshToken);
        String accessToken = jwtService.createAccessToken(rotation.userId(), rotation.role());

        return new AuthTokens(
                createTokenResponse(accessToken),
                rotation.refreshToken()
        );
    }

    /** 저장된 Refresh Token을 폐기한다. 이미 발급된 Access Token은 만료까지 남는다. */
    public void logout(String refreshToken) {
        if (refreshToken != null && !refreshToken.isBlank()) {
            refreshTokenService.revoke(refreshToken);
        }
    }

    private TokenResponse createTokenResponse(String accessToken) {
        return new TokenResponse(
                "Bearer", accessToken, jwtService.getExpirationSeconds());
    }
}
