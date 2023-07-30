package com.tidavid1.Studywith.domain.user.service;

import com.tidavid1.Studywith.domain.user.dto.UserLoginRequestDto;
import com.tidavid1.Studywith.domain.user.dto.UserSignupRequestDto;
import com.tidavid1.Studywith.domain.user.entity.User;
import com.tidavid1.Studywith.domain.user.exception.CIdLoginFailedException;
import com.tidavid1.Studywith.domain.user.exception.CIdSignupFailedException;
import com.tidavid1.Studywith.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserSignService {
    private final UserRepository userRepository;

    @Transactional
    public Long signup(UserSignupRequestDto userSignupRequestDto){
        if(userRepository.findById(userSignupRequestDto.getId()).isPresent()){
            throw new CIdSignupFailedException();
        }
        return userRepository.save(userSignupRequestDto.toEntity()).getUserId();
    }

    @Transactional
    public Long login(UserLoginRequestDto userLoginRequestDto){
        User user = userRepository.findById(userLoginRequestDto.getId()).orElseThrow(CIdLoginFailedException::new);
        if(!userLoginRequestDto.getPasswd().equals(user.getPasswd())){
            throw new CIdLoginFailedException();
        }
        return user.getUserId();
    }
}
