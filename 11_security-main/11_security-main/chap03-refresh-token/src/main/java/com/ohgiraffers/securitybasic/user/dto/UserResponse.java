package com.ohgiraffers.securitybasic.user.dto;

import com.ohgiraffers.securitybasic.user.domain.UserAccount;

public record UserResponse(
        Long id,
        String userId,
        String userName,
        String email,
        String role
) {
    public static UserResponse from(UserAccount user) {
        return new UserResponse(
                user.getId(),
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}
