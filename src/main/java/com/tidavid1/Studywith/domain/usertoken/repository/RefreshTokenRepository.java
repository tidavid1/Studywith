package com.tidavid1.Studywith.domain.usertoken.repository;

import com.tidavid1.Studywith.domain.usertoken.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    Optional<RefreshToken> findByRefreshTokenKey(Long refreshTokenKey);
}
