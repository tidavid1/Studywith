package com.tidavid1.Studywith.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(title = "User Login Request", description = "유저 로그인 입력 모델")
public class UserLoginRequestDto {
    @Schema(title = "id", description = "ID", example = "test")
    private String id;
    @Schema(title = "passwd", description = "Password", example = "test!")
    private String passwd;
}
