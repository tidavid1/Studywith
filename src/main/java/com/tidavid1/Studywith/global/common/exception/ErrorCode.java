package com.tidavid1.Studywith.global.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNKNOWN(-9999, "알 수 없는 오류"),
    UserNotFound(-1000, "찾는 유저가 존재하지 않음"),
    IdSignupFailed(-1001, "이미 가입된 아이디"),
    IdLoginFailed(-1002, "가입하지 않은 아이디거나 잘못된 비밀번호 입력"),
    TeachingNotFound(-1003, "수업이 존재하지 않음"),
    TeachingAlreadyExist(-1004, "수업이 이미 존재"),
    TeachingEndDateEarlierThenStartDate(-1005, "수업 종료 일자가 시작 일자보다 빠르거나 같음");

    private final int code;
    private final String message;
}
