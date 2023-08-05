package com.tidavid1.Studywith.domain.user.dto;

import com.tidavid1.Studywith.domain.user.entity.Role;
import com.tidavid1.Studywith.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "User Signup Request", description = "회원 가입용 입력 모델")
public class UserSignupRequestDto {
    @Schema(title = "id", description = "ID", example = "test")
    private String id;
    @Schema(title = "passwd", description = "Password", example = "test!")
    private String passwd;
    @Schema(title = "name", description = "Name", example = "홍길동")
    private String name;
    @Schema(title = "phoneCall", description = "Phone Call", example = "010-1111-1111")
    private String phoneCall;
    @Schema(title = "role", description = "Role")
    private Role role;

    public User toEntity(){
        return User.builder()
                .id(id)
                .passwd(passwd)
                .name(name)
                .phoneCall(phoneCall)
                .role(role)
                .build();
    }

}
