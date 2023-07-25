package com.tidavid1.Studywith.global.common.response.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "공통 응답 모델", description = "API 상태, 처리 여부, Message")
public class CommonResult {
    @Schema(title = "응답 여부", description = "응답 여부 Boolean Type 표현")
    private boolean success;
    @Schema(title = "응답 코드", description = " <= 0: 정상, > 0: 비정상")
    private int code;
    @Schema(title = "응답 Message")
    private String message;
}
