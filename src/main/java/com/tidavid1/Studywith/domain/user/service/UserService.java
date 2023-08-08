package com.tidavid1.Studywith.domain.user.service;

import com.tidavid1.Studywith.domain.user.dto.UserRequestDto;
import com.tidavid1.Studywith.domain.user.dto.UserResponseDto;
import com.tidavid1.Studywith.domain.user.entity.Role;
import com.tidavid1.Studywith.domain.user.entity.User;
import com.tidavid1.Studywith.domain.user.exception.CUserNotFoundException;
import com.tidavid1.Studywith.domain.user.repository.UserRepository;
import com.tidavid1.Studywith.domain.usertoken.config.JwtProvider;
import com.tidavid1.Studywith.domain.usertoken.exception.CAccessDeniedException;
import com.tidavid1.Studywith.domain.usertoken.exception.CAccessTokenInvalidException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    private Authentication getAuthByAccessToken(String accessToken) {
        if (!jwtProvider.validationToken(accessToken)) {
            throw new CAccessTokenInvalidException();
        }
        return jwtProvider.getAuthentication(accessToken);
    }

    @Transactional
    public UserResponseDto findByUserId(String accessToken, Long userId){
        Authentication authentication = getAuthByAccessToken(accessToken);
        if(((User) authentication.getPrincipal()).getRole().equals(Role.USER_Student)){
            throw new CAccessDeniedException();
        }
        User user = userRepository.findByUserId(userId).orElseThrow(CUserNotFoundException::new);
        return new UserResponseDto(user);
    }

    @Transactional
    public UserResponseDto findById(String accessToken, String id){
        Authentication authentication = getAuthByAccessToken(accessToken);
        if(((User) authentication.getPrincipal()).getRole().equals(Role.USER_Student)){
            throw new CAccessDeniedException();
        }
        User user = userRepository.findById(id).orElseThrow(CUserNotFoundException::new);
        return new UserResponseDto(user);
    }

    @Transactional
    public List<UserResponseDto> findByRole(String accessToken, Role role){
        Authentication authentication = getAuthByAccessToken(accessToken);
        if(((User) authentication.getPrincipal()).getRole().equals(Role.ADMIN)){
            return userRepository.findByRole(role).stream().map(UserResponseDto::new).collect(Collectors.toList());
        }else{
            throw new CAccessDeniedException();
        }
    }

    @Transactional
    public List<UserResponseDto> findAllUser(String accessToken){
        Authentication authentication = getAuthByAccessToken(accessToken);
        if(((User) authentication.getPrincipal()).getRole().equals(Role.ADMIN)){
            return userRepository.findAll().stream().map(UserResponseDto::new).collect(Collectors.toList());
        }else{
            throw new CAccessDeniedException();
        }
    }

    @Transactional
    public void updatePhoneCall(String accessToken, UserRequestDto userRequestDto){
        Authentication authentication = getAuthByAccessToken(accessToken);
        User user = userRepository.findByUserId(((User) authentication.getPrincipal()).getUserId()).orElseThrow(CUserNotFoundException::new);
        user.updatePhoneCall(userRequestDto.getPhoneCall());
    }

    @Transactional
    public UserResponseDto findByAccessToken(String accessToken){
        Authentication authentication = getAuthByAccessToken(accessToken);
        return new UserResponseDto(userRepository.findByUserId(((User) authentication.getPrincipal()).getUserId()).orElseThrow(CUserNotFoundException::new));
    }

}
