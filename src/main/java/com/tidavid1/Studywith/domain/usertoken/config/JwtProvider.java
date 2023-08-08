package com.tidavid1.Studywith.domain.usertoken.config;

import com.tidavid1.Studywith.domain.usertoken.dto.TokenDto;
import com.tidavid1.Studywith.domain.usertoken.exception.CAuthenticationEntryPointException;
import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtProvider {
    private final UserDetailsService userDetailsService;
    private final String ROLES = "roles";
    @Value("spring.jwt.secret")
    private String secretKey;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public TokenDto createToken(Long userPk, List<String> roles){
        long ACCESS_TOKEN_VALID_MS = 60 * 60 * 1000L;
        long REFRESH_TOKEN_VALID_MS = 14*24*60*60*1000L;
        Claims claims = Jwts.claims().setSubject(String.valueOf(userPk));
        claims.put(ROLES, roles);
        Date currentDate = new Date();

        return TokenDto.builder()
                .grantType("Bearer")
                .accessToken(
                        Jwts.builder()
                                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                                .setClaims(claims)
                                .setIssuedAt(currentDate)
                                .setExpiration(new Date(currentDate.getTime()+ ACCESS_TOKEN_VALID_MS))
                                .signWith(SignatureAlgorithm.HS256, secretKey)
                                .compact())
                .refreshToken(
                        Jwts.builder()
                                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                                .setExpiration(new Date(currentDate.getTime() + REFRESH_TOKEN_VALID_MS))
                                .signWith(SignatureAlgorithm.HS256, secretKey)
                                .compact())
                .accessTokenExpireDate(ACCESS_TOKEN_VALID_MS)
                .build();
    }

    public Authentication getAuthentication(String token){
        Claims claims = parseClaims(token);
        if(claims.get(ROLES)==null){
            throw new CAuthenticationEntryPointException();
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }


    private Claims parseClaims(String token){
        try{
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token).getBody();
        }catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }

    public String resolveToken(HttpServletRequest request){
        return request.getHeader("X-AUTH-TOKEN");
    }

    public boolean validationToken(String token){
        try{
            Jwt<Header, Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (Exception e){
            return false;
        }
    }

}
