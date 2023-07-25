package com.tidavid1.Studywith.global.common.response.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(title = "다중 응답 모델", description = "API 반환 값이 다중 객체일 경우 처리")
public class ListResult<T> extends CommonResult{
    private List<T> data;
}
