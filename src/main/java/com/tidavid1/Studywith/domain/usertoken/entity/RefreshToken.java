package com.tidavid1.Studywith.domain.usertoken.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Table(name = "refreshToken")
@Entity
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refreshTokenId;

    @Column(nullable = false)
    private Long refreshTokenKey;

    @Column(nullable = false)
    private String refreshToken;

    @Column(nullable = false)
    private LocalDate createdDate;

    public void updateToken(String refreshToken){
        this.refreshToken = refreshToken;
    }

    @Builder
    public RefreshToken(Long refreshTokenKey, String refreshToken, LocalDate createdDate){
        this.refreshTokenKey = refreshTokenKey;
        this.refreshToken = refreshToken;
        this.createdDate = createdDate;

    }
}
