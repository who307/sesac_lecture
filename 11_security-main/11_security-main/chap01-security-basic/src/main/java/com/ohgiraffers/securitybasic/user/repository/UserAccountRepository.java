package com.ohgiraffers.securitybasic.user.repository;

import com.ohgiraffers.securitybasic.user.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findByUserId(String userId);

    boolean existsByUserId(String userId);

    boolean existsByEmail(String email);
}
