package com.tidavid1.Studywith.domain.usertoken.config;

import com.tidavid1.Studywith.domain.usertoken.dto.TokenDto;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class TokenProvider {
    @Value("${spring.jwt.secret}")
    private String secretKey;

    @Value("${spring.jwt.issuer}")
    private String issuer;
    private JwtParser jwtParser;
    private final String GRANT_TYPE ="Bearer";
    private final long ACCESS_TOKEN_VALIDATION_HOURS = 1L;

    @PostConstruct
    protected void init() {
        jwtParser = Jwts
                .parserBuilder()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                .build();
    }

    public TokenDto createToken(String userSpecification) {
        long refreshTokenValidationHours = 336L;
        return TokenDto
                .builder()
                .grantType(GRANT_TYPE)
                .accessToken(
                        Jwts
                                .builder()
                                .signWith(new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS512.getJcaName()))
                                .setSubject(userSpecification)
                                .setIssuer(issuer)
                                .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
                                .setExpiration(Date.from(Instant.now().plus(ACCESS_TOKEN_VALIDATION_HOURS, ChronoUnit.HOURS)))
                                .compact()
                )
                .refreshToken(
                        Jwts
                                .builder()
                                .signWith(new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS512.getJcaName()))
                                .setIssuer(issuer)
                                .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
                                .setExpiration(Date.from(Instant.now().plus(refreshTokenValidationHours, ChronoUnit.HOURS)))
                                .compact()
                )
                .accessTokenExpireDate(Date.from(Instant.now().plus(ACCESS_TOKEN_VALIDATION_HOURS, ChronoUnit.HOURS)).getTime())
                .build();
    }

    public TokenDto createToken(String userSpecification, String refreshToken){
        return TokenDto
                .builder()
                .grantType(GRANT_TYPE)
                .accessToken(
                        Jwts
                                .builder()
                                .signWith(new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS512.getJcaName()))
                                .setSubject(userSpecification)
                                .setIssuer(issuer)
                                .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
                                .setExpiration(Date.from(Instant.now().plus(ACCESS_TOKEN_VALIDATION_HOURS, ChronoUnit.HOURS)))
                                .compact()
                )
                .refreshToken(refreshToken)
                .accessTokenExpireDate(Date.from(Instant.now().plus(ACCESS_TOKEN_VALIDATION_HOURS, ChronoUnit.HOURS)).getTime())
                .build();
    }


    public String[] resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization").split(" ");
    }

    public boolean isValidateToken(String token) {
        try {
            return jwtParser
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration()
                    .after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String validateTokenAndGetSubject(String token) {
        return jwtParser
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
