package com.tidavid1.Studywith.domain.user.controller;

import com.tidavid1.Studywith.domain.user.dto.UserRequestDto;
import com.tidavid1.Studywith.domain.user.dto.UserResponseDto;
import com.tidavid1.Studywith.domain.user.service.UserService;
import com.tidavid1.Studywith.global.common.response.model.ListResult;
import com.tidavid1.Studywith.global.common.response.model.SingleResult;
import com.tidavid1.Studywith.global.common.response.service.ResponseFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "UserController")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @Operation(summary = "userId 기반 유저 단건 검색", description = "userId(Long)로 유저를 조회합니다.")
    @GetMapping("/user/userId/{userId}")
    public SingleResult<UserResponseDto> findUserByUserId(
            @Parameter(description = "userId", required = true) @PathVariable Long userId
    ){
        return ResponseFactory.getSingleResult(userService.findByUserId(userId));
    }

    @Operation(summary = "id 기반 유저 단건 검색", description = "id(String)로 유저를 조회합니다.")
    @GetMapping("/user/id/{id}")
    public SingleResult<UserResponseDto> findUserById(
            @Parameter(description = "id", required = true) @PathVariable String id
    ){
        return ResponseFactory.getSingleResult(userService.findById(id));
    }

    @Operation(summary = "유저 전체 검색", description = "유저 전체를 조회합니다.")
    @GetMapping("/users")
    public ListResult<UserResponseDto> findAllUser(){
        return ResponseFactory.getListResult(userService.findAllUser());
    }

    @Operation(summary = "유저 전화번호 갱신", description = "유저 전화번호를 갱신합니다.")
    @PutMapping("/user")
    public SingleResult<Long> updatePhoneCall(
            @Parameter(description = "userId", required = true) @RequestParam Long userId,
            @Parameter(description = "phoneCall", required = true) @RequestParam String phoneCall
    ){
        UserRequestDto userRequestDto = UserRequestDto.builder().phoneCall(phoneCall).build();
        return ResponseFactory.getSingleResult(userService.updatePhoneCall(userId, userRequestDto));
    }

}
