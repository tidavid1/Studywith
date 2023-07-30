package com.tidavid1.Studywith.domain.user.controller;

import com.tidavid1.Studywith.domain.user.dto.UserLoginRequestDto;
import com.tidavid1.Studywith.domain.user.dto.UserSignupRequestDto;
import com.tidavid1.Studywith.domain.user.service.UserSignService;
import com.tidavid1.Studywith.global.common.response.model.SingleResult;
import com.tidavid1.Studywith.global.common.response.service.ResponseFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Signup/Login")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserSignController {
    private final UserSignService userSignService;

    @Operation(summary = "Signup", description = "회원 가입")
    @PostMapping("/signup")
    public SingleResult<Long> signup(
            @Parameter(description = "Signup Request DTO", required = true)
            @RequestBody UserSignupRequestDto userSignupRequestDto){
        return ResponseFactory.getSingleResult(userSignService.signup(userSignupRequestDto));
    }

    @Operation(summary = "Login", description = "로그인")
    @PostMapping("/login")
    public SingleResult<Long> login(
        @Parameter(description = "Login Request DTO", required = true)
        @RequestBody UserLoginRequestDto userLoginRequestDto){
        return ResponseFactory.getSingleResult(userSignService.login(userLoginRequestDto));
    }
}
