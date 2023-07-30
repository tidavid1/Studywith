package com.tidavid1.Studywith.global.common.exception;

import com.tidavid1.Studywith.domain.user.exception.CIdLoginFailedException;
import com.tidavid1.Studywith.domain.user.exception.CIdSignupFailedException;
import com.tidavid1.Studywith.domain.user.exception.CUserNotFoundException;
import com.tidavid1.Studywith.global.common.response.model.CommonResult;
import com.tidavid1.Studywith.global.common.response.service.ResponseFactory;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult defaultException(Exception e){
        return ResponseFactory.getFailResult(ErrorCode.UNKNOWN.getCode(), ErrorCode.UNKNOWN.getMessage());
    }

    @ExceptionHandler(CUserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult userNotFoundException(HttpServletRequest request, CUserNotFoundException e){
        return ResponseFactory.getFailResult(ErrorCode.UserNotFound.getCode(), ErrorCode.UserNotFound.getMessage());
    }

    @ExceptionHandler(CIdSignupFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult idSignupFailedException(HttpServletRequest request, CIdSignupFailedException e){
        return ResponseFactory.getFailResult(ErrorCode.IdSignupFailed.getCode(), ErrorCode.IdSignupFailed.getMessage());
    }

    @ExceptionHandler(CIdLoginFailedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected CommonResult idLoginFailedException(HttpServletRequest request, CIdLoginFailedException e){
        return ResponseFactory.getFailResult(ErrorCode.IdLoginFailed.getCode(), ErrorCode.IdLoginFailed.getMessage());
    }

}
