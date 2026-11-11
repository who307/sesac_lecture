package com.ohgiraffers.securitybasic.user.controller;

import com.ohgiraffers.securitybasic.auth.AuthService;
import com.ohgiraffers.securitybasic.auth.AuthTokens;
import com.ohgiraffers.securitybasic.auth.dto.LoginRequest;
import com.ohgiraffers.securitybasic.auth.dto.TokenResponse;
import com.ohgiraffers.securitybasic.user.dto.SignupRequest;
import com.ohgiraffers.securitybasic.user.dto.UserResponse;
import com.ohgiraffers.securitybasic.user.service.UserAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

/**
 * 로그인·재발급·로그아웃 요청을 받는 인증 API다.
 * Access Token은 응답 본문에, Refresh Token은 HttpOnly Cookie에 담아 보낸다.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserAccountService userAccountService;
    private final AuthService authService;
    private final long refreshExpirationSeconds;
    private final boolean cookieSecure;

    public AuthController(UserAccountService userAccountService,
                          AuthService authService,
                          @Value("${security.refresh-token.expiration-seconds}")
                          long refreshExpirationSeconds,
                          @Value("${security.refresh-token.cookie-secure:false}")
                          boolean cookieSecure) {
        this.userAccountService = userAccountService;
        this.authService = authService;
        this.refreshExpirationSeconds = refreshExpirationSeconds;
        this.cookieSecure = cookieSecure;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signup(@Valid @RequestBody SignupRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userAccountService.signup(request));
    }

    /** 비밀번호 로그인에 성공하면 두 토큰을 각 전달 위치에 담아 응답한다. */
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthTokens tokens = authService.login(request);
        return tokenResponse(tokens);
    }

    /** Cookie의 Refresh Token을 검사하고 새 Access Token과 Cookie를 응답한다. */
    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(
            @CookieValue(name = "refresh_token", required = false)
            String refreshToken) {
        AuthTokens tokens = authService.refresh(refreshToken);
        return tokenResponse(tokens);
    }

    /** 저장된 Refresh Token을 폐기하고 브라우저의 Cookie를 만료시킨다. */
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @CookieValue(name = "refresh_token", required = false)
            String refreshToken) {
        authService.logout(refreshToken);
        return ResponseEntity.noContent()
                .header(HttpHeaders.SET_COOKIE, clearRefreshCookie().toString())
                .build();
    }

    private ResponseEntity<TokenResponse> tokenResponse(AuthTokens tokens) {
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE,
                        refreshCookie(tokens.refreshToken()).toString())
                .body(tokens.tokenResponse());
    }

    private ResponseCookie refreshCookie(String refreshToken) {
        // JavaScript에서는 읽지 못하게 하고, 인증 API 경로에만 Cookie를 보낸다.
        return ResponseCookie.from("refresh_token", refreshToken)
                .httpOnly(true)
                .secure(cookieSecure) // HTTPS 전송을 요구할지 정한다. 로컬은 false, 운영은 true다.
                .sameSite("Strict") // 다른 사이트에서 시작된 요청에는 보내지 않는다.
                .path("/api/auth") // /api/auth와 그 하위 경로에만 보낸다.
                .maxAge(Duration.ofSeconds(refreshExpirationSeconds)) // 브라우저가 쿠키를 보관할 기간
                .build();
    }

    private ResponseCookie clearRefreshCookie() {
        // 같은 이름과 경로의 Cookie에 Max-Age=0을 내려 브라우저 값을 지운다.
        return ResponseCookie.from("refresh_token", "")
                .httpOnly(true)
                .secure(cookieSecure)
                .sameSite("Strict")
                .path("/api/auth")
                .maxAge(Duration.ZERO)
                .build();
    }
}
