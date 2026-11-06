package com.ohgiraffers.securitybasic.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequest(
        @NotBlank @Size(max = 50) String userId,
        @NotBlank @Size(min = 8, max = 100) String password,
        @NotBlank @Size(max = 50) String userName,
        @NotBlank @Email @Size(max = 100) String email
) {
}
