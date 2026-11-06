package com.ohgiraffers.securitybasic.auth.repository;

import com.ohgiraffers.securitybasic.auth.domain.RefreshToken;
import com.ohgiraffers.securitybasic.user.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByTokenHash(String tokenHash);

    Optional<RefreshToken> findByUser(UserAccount user);
}
