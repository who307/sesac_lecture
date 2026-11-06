package com.ohgiraffers.securitybasic.user.controller;

import com.ohgiraffers.securitybasic.user.dto.UserResponse;
import com.ohgiraffers.securitybasic.user.service.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserAccountService userAccountService;

    public UserController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> findMyProfile(Authentication authentication) {
        return ResponseEntity.ok(
                userAccountService.findByUserId(authentication.getName())
        );
    }
}
