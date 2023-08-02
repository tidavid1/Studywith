package com.tidavid1.Studywith.domain.user.dto;

import com.tidavid1.Studywith.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private String id;
    private String name;
    private String phoneCall;

    public User toEntity(){
        return User.builder()
                .id(id)
                .name(name)
                .phoneCall(phoneCall)
                .build();
    }
}
