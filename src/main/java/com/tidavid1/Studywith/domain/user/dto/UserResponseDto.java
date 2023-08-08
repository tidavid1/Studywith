package com.tidavid1.Studywith.domain.user.dto;

import com.tidavid1.Studywith.domain.user.entity.Role;
import com.tidavid1.Studywith.domain.user.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class UserResponseDto {
    private final Long userId;
    private final String id;
    private final String name;
    private final String phoneCall;
    private final Role role;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserResponseDto(User user){
        this.userId = user.getUserId();
        this.id = user.getId();
        this.name = user.getName();
        this.phoneCall = user.getPhoneCall();
        this.role = user.getRole();
        this.authorities = user.getAuthorities();
    }
}
