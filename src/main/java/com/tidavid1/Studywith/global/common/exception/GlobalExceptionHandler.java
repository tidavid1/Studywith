package com.tidavid1.Studywith.global.common.exception;

import com.tidavid1.Studywith.domain.studylog.exception.CStudyLogAlreadyExistException;
import com.tidavid1.Studywith.domain.studylog.exception.CStudyLogNotFoundException;
import com.tidavid1.Studywith.domain.teaching.exception.CTeachingAlreadyExistException;
import com.tidavid1.Studywith.domain.teaching.exception.CTeachingEndDateEarlierThenStartDateException;
import com.tidavid1.Studywith.domain.teaching.exception.CTeachingNotFoundException;
import com.tidavid1.Studywith.domain.user.exception.CIdLoginFailedException;
import com.tidavid1.Studywith.domain.user.exception.CIdSignupFailedException;
import com.tidavid1.Studywith.domain.user.exception.CUserNotFoundException;
import com.tidavid1.Studywith.domain.usertoken.exception.*;
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

    @ExceptionHandler(CTeachingNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult teachingNotFoundException(HttpServletRequest request, CTeachingNotFoundException e){
        return ResponseFactory.getFailResult(ErrorCode.TeachingNotFound.getCode(), ErrorCode.TeachingNotFound.getMessage());
    }

    @ExceptionHandler(CTeachingAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult teachingAlreadyExistException(HttpServletRequest request, CTeachingAlreadyExistException e){
        return ResponseFactory.getFailResult(ErrorCode.TeachingAlreadyExist.getCode(), ErrorCode.TeachingAlreadyExist.getMessage());
    }

    @ExceptionHandler(CTeachingEndDateEarlierThenStartDateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult teachingEndDateEarlierThenStartDateException(HttpServletRequest request, CTeachingEndDateEarlierThenStartDateException e){
        return ResponseFactory.getFailResult(ErrorCode.TeachingEndDateEarlierThenStartDate.getCode(), ErrorCode.TeachingEndDateEarlierThenStartDate.getMessage());
    }

    @ExceptionHandler(CStudyLogNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult studyLogNotFoundException(HttpServletRequest request, CStudyLogNotFoundException e){
        return ResponseFactory.getFailResult(ErrorCode.StudyLogNotFound.getCode(), ErrorCode.StudyLogNotFound.getMessage());
    }

    @ExceptionHandler(CStudyLogAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult studyLogAlreadyExistException(HttpServletRequest request, CStudyLogAlreadyExistException e){
        return ResponseFactory.getFailResult(ErrorCode.StudyLogAlreadyExist.getCode(), ErrorCode.StudyLogAlreadyExist.getMessage());
    }

    @ExceptionHandler(CAccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected CommonResult accessDeniedException(HttpServletRequest request, CAccessDeniedException e){
        return ResponseFactory.getFailResult(ErrorCode.AccessDenied.getCode(), ErrorCode.AccessDenied.getMessage());
    }

    @ExceptionHandler(CAccessTokenExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected CommonResult accessTokenExpiredException(HttpServletRequest request, CAccessTokenExpiredException e){
        return ResponseFactory.getFailResult(ErrorCode.AccessTokenExpired.getCode(), ErrorCode.AccessTokenExpired.getMessage());
    }

    @ExceptionHandler(CAccessTokenInvalidException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected CommonResult accessTokenInvalidException(HttpServletRequest request, CAccessTokenInvalidException e){
        return ResponseFactory.getFailResult(ErrorCode.AccessTokenInvalid.getCode(), ErrorCode.AccessTokenInvalid.getMessage());
    }

    @ExceptionHandler(CAuthenticationEntryPointException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected CommonResult authenticationEntryPointException(HttpServletRequest request, CAuthenticationEntryPointException e){
        return ResponseFactory.getFailResult(ErrorCode.AuthenticationEntryPoint.getCode(), ErrorCode.AuthenticationEntryPoint.getMessage());
    }

    @ExceptionHandler(CRefreshTokenInvalidException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected CommonResult refreshTokenInvalidException(HttpServletRequest request, CRefreshTokenInvalidException e){
        return ResponseFactory.getFailResult(ErrorCode.RefreshTokenInvalid.getCode(), ErrorCode.RefreshTokenInvalid.getMessage());
    }
}
