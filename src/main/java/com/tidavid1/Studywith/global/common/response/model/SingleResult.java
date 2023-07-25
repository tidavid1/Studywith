package com.tidavid1.Studywith.global.common.response.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "단일 응답 모델", description = "API 반환 값이 단일 객체인 경우 처리")
public class SingleResult<T> extends CommonResult{
    private T data;
}
