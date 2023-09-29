package com.tidavid1.Studywith.domain.user.controller;

import com.tidavid1.Studywith.domain.user.dto.UserRequestDto;
import com.tidavid1.Studywith.domain.user.dto.UserResponseDto;
import com.tidavid1.Studywith.domain.user.entity.Role;
import com.tidavid1.Studywith.domain.user.service.UserService;
import com.tidavid1.Studywith.global.common.response.model.CommonResult;
import com.tidavid1.Studywith.global.common.response.model.ListResult;
import com.tidavid1.Studywith.global.common.response.model.SingleResult;
import com.tidavid1.Studywith.global.common.response.service.ResponseFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "UserController")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Parameter(
            name = "X-AUTH-TOKEN",
            description = "로그인 성공 후 AccessToken",
            required = true,
            schema = @Schema(type = "string"),
            in = ParameterIn.HEADER)
    @Operation(summary = "userId 기반 유저 단건 검색", description = "userId(Long)로 유저를 조회합니다.")
    @GetMapping("/userId/{userId}")
    public SingleResult<UserResponseDto> findUserByUserId(
            @Parameter(description = "userId", required = true) @PathVariable Long userId
    ) {
        return ResponseFactory.getSingleResult(userService.findByUserId(userId));
    }

    @Parameter(
            name = "X-AUTH-TOKEN",
            description = "로그인 성공 후 AccessToken",
            required = true,
            schema = @Schema(type = "string"),
            in = ParameterIn.HEADER)
    @Operation(summary = "id 기반 유저 단건 검색", description = "id(String)로 유저를 조회합니다.")
    @GetMapping("/id/{id}")
    public SingleResult<UserResponseDto> findUserById(
            @Parameter(description = "id", required = true) @PathVariable String id
    ) {
        return ResponseFactory.getSingleResult(userService.findById(id));
    }

    @Parameter(
            name = "X-AUTH-TOKEN",
            description = "로그인 성공 후 AccessToken",
            required = true,
            schema = @Schema(type = "string"),
            in = ParameterIn.HEADER)
    @Operation(summary = "유저 전체 검색", description = "유저 전체를 조회합니다.")
    @GetMapping("/find")
    public ListResult<UserResponseDto> findAllUser() {
        return ResponseFactory.getListResult(userService.findAllUser());
    }

    @Parameter(
            name = "X-AUTH-TOKEN",
            description = "로그인 성공 후 AccessToken",
            required = true,
            schema = @Schema(type = "string"),
            in = ParameterIn.HEADER)
    @Operation(summary = "Role 유저 전체 검색", description = "Role에 따른 유저들을 조회합니다.")
    @GetMapping("/find/role/{role}")
    public ListResult<UserResponseDto> findRoleUsers(
            @Parameter(description = "role", required = true) @PathVariable Role role) {
        return ResponseFactory.getListResult(userService.findByRole(role));
    }


    @Parameter(
            name = "X-AUTH-TOKEN",
            description = "로그인 성공 후 AccessToken",
            required = true,
            schema = @Schema(type = "string"),
            in = ParameterIn.HEADER)
    @Operation(summary = "유저 전화번호 갱신", description = "유저 전화번호를 갱신합니다.")
    @PutMapping("")
    public CommonResult updatePhoneCall(
            @Parameter(description = "phoneCall", required = true) @RequestParam String phoneCall
    ) {
        UserRequestDto userRequestDto = UserRequestDto.builder().phoneCall(phoneCall).build();
        userService.updatePhoneCall(userRequestDto);
        return ResponseFactory.getSuccessResult();
    }

}
