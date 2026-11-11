package com.ohgiraffers.securitybasic.auth;

import com.ohgiraffers.securitybasic.auth.dto.TokenResponse;

/** 서비스가 만든 두 토큰을 컨트롤러까지 함께 전달하는 내부 결과다. */
public record AuthTokens(
        TokenResponse tokenResponse,
        String refreshToken
) {
}
