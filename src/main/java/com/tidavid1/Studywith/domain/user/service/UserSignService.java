package com.tidavid1.Studywith.domain.user.service;

import com.tidavid1.Studywith.domain.user.dto.UserLoginRequestDto;
import com.tidavid1.Studywith.domain.user.dto.UserSignupRequestDto;
import com.tidavid1.Studywith.domain.user.entity.User;
import com.tidavid1.Studywith.domain.user.exception.CIdLoginFailedException;
import com.tidavid1.Studywith.domain.user.exception.CIdSignupFailedException;
import com.tidavid1.Studywith.domain.user.repository.UserRepository;
import com.tidavid1.Studywith.domain.usertoken.config.JwtProvider;
import com.tidavid1.Studywith.domain.usertoken.dto.TokenDto;
import com.tidavid1.Studywith.domain.usertoken.dto.TokenRequestDto;
import com.tidavid1.Studywith.domain.usertoken.entity.RefreshToken;
import com.tidavid1.Studywith.domain.usertoken.exception.CRefreshTokenInvalidException;
import com.tidavid1.Studywith.domain.usertoken.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@RequiredArgsConstructor
@Service
public class UserSignService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public Long signup(UserSignupRequestDto userSignupRequestDto){
        if(userRepository.findById(userSignupRequestDto.getId()).isPresent()){
            throw new CIdSignupFailedException();
        }
        return userRepository.save(userSignupRequestDto.toEntity(passwordEncoder)).getUserId();
    }

    @Transactional
    public TokenDto login(UserLoginRequestDto userLoginRequestDto){
        User user = userRepository.findById(userLoginRequestDto.getId()).orElseThrow(CIdLoginFailedException::new);
        if(!passwordEncoder.matches(userLoginRequestDto.getPasswd(), user.getPassword())){
            throw new CIdLoginFailedException();
        }
        TokenDto tokenDto = jwtProvider.createToken(user.getUserId(), user.getRole());
        refreshTokenRepository.save(tokenDto.toEntity(user));
        return tokenDto;
    }

    @Transactional
    public TokenDto reissue(TokenRequestDto tokenRequestDto){
        if(!jwtProvider.validationToken(tokenRequestDto.getRefreshToken())){
            throw new CRefreshTokenInvalidException();
        }
        String accessToken = tokenRequestDto.getAccessToken();
        Authentication authentication = jwtProvider.getAuthentication(accessToken);
        User user = userRepository.findByUserId(((User) authentication.getPrincipal()).getUserId()).orElseThrow(CRefreshTokenInvalidException::new);
        RefreshToken refreshToken = refreshTokenRepository.findByRefreshTokenKey(user.getUserId()).orElseThrow(CRefreshTokenInvalidException::new);
        if(!refreshToken.getRefreshToken().equals(tokenRequestDto.getRefreshToken())){
            throw new CRefreshTokenInvalidException();
        }
        TokenDto newCreatedToken = jwtProvider.createToken(user.getUserId(), user.getRole());
        refreshToken.updateToken(newCreatedToken.getRefreshToken());
        return newCreatedToken;
    }
}
