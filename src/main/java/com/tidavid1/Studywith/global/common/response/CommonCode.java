package com.tidavid1.Studywith.global.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonCode {
    SUCCESS(0, "SUCCESS"),
    FAIL(-1, "FAIL");

    private final int code;
    private final String message;
}
