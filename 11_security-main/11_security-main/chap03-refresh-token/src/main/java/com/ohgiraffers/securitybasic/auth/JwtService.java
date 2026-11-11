package com.ohgiraffers.securitybasic.auth;

import com.ohgiraffers.securitybasic.user.domain.UserRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

/** 로그인과 재발급에서 사용할 Access Token을 만드는 서비스다. */
@Service
public class JwtService {

    private final JwtEncoder jwtEncoder;
    private final long expirationSeconds;

    public JwtService(JwtEncoder jwtEncoder,
                      @Value("${security.jwt.expiration-seconds}") long expirationSeconds) {
        this.jwtEncoder = jwtEncoder;
        this.expirationSeconds = expirationSeconds;
    }

    /** 비밀번호 로그인의 인증 결과에서 아이디와 권한을 읽어 발급한다. */
    public String createAccessToken(Authentication authentication) {
        List<String> authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return createAccessToken(authentication.getName(), authorities);
    }

    /** Refresh Token을 검증한 회원 정보로 새 Access Token을 발급한다. */
    public String createAccessToken(String userId, UserRole role) {
        return createAccessToken(userId, List.of("ROLE_" + role.name()));
    }

    private String createAccessToken(String subject, List<String> authorities) {
        Instant issuedAt = Instant.now();

        // 새 ID를 넣어 같은 회원에게 연속 발급해도 토큰 문자열을 구분한다.
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("chap03-refresh-token")
                .issuedAt(issuedAt)
                .expiresAt(issuedAt.plusSeconds(expirationSeconds))
                .subject(subject)
                .id(UUID.randomUUID().toString())
                .claim("authorities", authorities)
                .build();

        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();
        return jwtEncoder.encode(JwtEncoderParameters.from(header, claims))
                .getTokenValue();
    }

    public long getExpirationSeconds() {
        return expirationSeconds;
    }
}
