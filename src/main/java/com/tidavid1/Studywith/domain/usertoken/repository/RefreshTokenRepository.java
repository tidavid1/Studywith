package com.tidavid1.Studywith.domain.usertoken.repository;

import com.tidavid1.Studywith.domain.usertoken.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    Optional<RefreshToken> findByRefreshTokenKey(Long refreshTokenKey);

    @Query("SELECT r FROM RefreshToken r WHERE r.refreshToken = :refreshToken")
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
