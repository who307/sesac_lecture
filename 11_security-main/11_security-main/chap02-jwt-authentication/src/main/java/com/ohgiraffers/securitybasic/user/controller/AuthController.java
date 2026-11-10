package com.ohgiraffers.securitybasic.user.controller;

import com.ohgiraffers.securitybasic.auth.AuthService;
import com.ohgiraffers.securitybasic.auth.dto.LoginRequest;
import com.ohgiraffers.securitybasic.auth.dto.TokenResponse;
import com.ohgiraffers.securitybasic.user.dto.SignupRequest;
import com.ohgiraffers.securitybasic.user.dto.UserResponse;
import com.ohgiraffers.securitybasic.user.service.UserAccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserAccountService userAccountService;
    private final AuthService authService;

    public AuthController(UserAccountService userAccountService, AuthService authService) {
        this.userAccountService = userAccountService;
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signup(@Valid @RequestBody SignupRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userAccountService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
