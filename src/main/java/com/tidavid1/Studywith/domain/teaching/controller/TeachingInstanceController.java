package com.tidavid1.Studywith.domain.teaching.controller;


import com.tidavid1.Studywith.domain.teaching.dto.TeachingInstanceResponseDto;
import com.tidavid1.Studywith.domain.teaching.service.TeachingInstanceService;
import com.tidavid1.Studywith.global.common.response.model.CommonResult;
import com.tidavid1.Studywith.global.common.response.model.SingleResult;
import com.tidavid1.Studywith.global.common.response.service.ResponseFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "TeachingInstanceController")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/teachingInstance")
public class TeachingInstanceController {
    private final TeachingInstanceService teachingInstanceService;

    @Parameter(
            name = "X-AUTH-TOKEN",
            description = "로그인 성공 후 AccessToken",
            required = true,
            schema = @Schema(type = "string"),
            in = ParameterIn.HEADER)
    @Operation(summary = "수업 Instance 시작", description = "수업 Instance를 시작합니다.")
    @GetMapping("/start/{teachingId}")
    public SingleResult<TeachingInstanceResponseDto> startInstance(
            @Parameter(description = "teachingId", required = true) @PathVariable Long teachingId) {
        return ResponseFactory.getSingleResult(teachingInstanceService.startTeachingInstance(teachingId));
    }

    @Parameter(
            name = "X-AUTH-TOKEN",
            description = "로그인 성공 후 AccessToken",
            required = true,
            schema = @Schema(type = "string"),
            in = ParameterIn.HEADER)
    @Operation(summary = "수업 Instance 중지", description = "수업 Instance를 중지합니다.")
    @GetMapping("/stop/{teachingId}")
    public CommonResult stopInstance(
            @Parameter(description = "teachingId", required = true) @PathVariable Long teachingId) {
        teachingInstanceService.stopTeachingInstance(teachingId);
        return ResponseFactory.getSuccessResult();
    }

    @Parameter(
            name = "X-AUTH-TOKEN",
            description = "로그인 성공 후 AccessToken",
            required = true,
            schema = @Schema(type = "string"),
            in = ParameterIn.HEADER)
    @Operation(summary = "수업 Instance 종료", description = "수업 Instance를 종료합니다.")
    @GetMapping("/terminate/{teachingId}")
    public CommonResult terminateInstance(
            @Parameter(description = "teachingId", required = true) @PathVariable Long teachingId) {
        teachingInstanceService.terminateTeachingInstance(teachingId);
        return ResponseFactory.getSuccessResult();
    }
}
