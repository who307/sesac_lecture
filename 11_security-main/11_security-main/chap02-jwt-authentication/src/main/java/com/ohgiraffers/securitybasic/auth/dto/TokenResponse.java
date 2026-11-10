package com.ohgiraffers.securitybasic.auth.dto;

public record TokenResponse(
        String tokenType,
        String accessToken,
        long expiresIn
) {
}
