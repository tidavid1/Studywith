package com.tidavid1.Studywith.domain.user.service;

import com.tidavid1.Studywith.domain.user.dto.UserRequestDto;
import com.tidavid1.Studywith.domain.user.dto.UserResponseDto;
import com.tidavid1.Studywith.domain.user.entity.Role;
import com.tidavid1.Studywith.domain.user.entity.User;
import com.tidavid1.Studywith.domain.user.exception.CUserNotFoundException;
import com.tidavid1.Studywith.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto findByUserId(Long userId){
        User user = userRepository.findByUserId(userId).orElseThrow(CUserNotFoundException::new);
        return new UserResponseDto(user);
    }

    @Transactional
    public UserResponseDto findById(String id){
        User user = userRepository.findById(id).orElseThrow(CUserNotFoundException::new);
        return new UserResponseDto(user);
    }

    @Transactional
    public List<UserResponseDto> findByRole(Role role){
        return userRepository.findByRole(role).stream().map(UserResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public List<UserResponseDto> findAllUser(){
        return userRepository.findAll().stream().map(UserResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void updatePhoneCall(UserRequestDto userRequestDto){
        User user = userRepository.findById(userRequestDto.getId()).orElseThrow(CUserNotFoundException::new);
        user.updatePhoneCall(userRequestDto.getPhoneCall());
    }

}
