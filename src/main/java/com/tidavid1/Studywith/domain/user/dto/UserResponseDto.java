package com.tidavid1.Studywith.domain.user.dto;

import com.tidavid1.Studywith.domain.user.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private final Long userId;
    private final String id;
    private final String name;
    private final String phoneCall;

    public UserResponseDto(User user){
        this.userId = user.getUserId();
        this.id = user.getId();
        this.name = user.getName();
        this.phoneCall = user.getPhoneCall();
    }
}
