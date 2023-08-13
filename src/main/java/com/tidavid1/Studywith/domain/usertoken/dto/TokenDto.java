package com.tidavid1.Studywith.domain.usertoken.dto;

import com.tidavid1.Studywith.domain.user.entity.User;
import com.tidavid1.Studywith.domain.usertoken.entity.RefreshToken;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpireDate;

    public RefreshToken toEntity(User user){
        return RefreshToken.builder()
                .refreshTokenKey(user.getUserId())
                .refreshToken(refreshToken)
                .createdDate(LocalDate.now())
                .build();
    }
}
