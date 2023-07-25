package com.tidavid1.Studywith.global.common.exception;

import com.tidavid1.Studywith.global.common.response.model.CommonResult;
import com.tidavid1.Studywith.global.common.response.service.ResponseFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setup(){
        globalExceptionHandler = new GlobalExceptionHandler();
    }
    @DisplayName("Test DefaultException")
    @Test
    void testDefaultException(){
        // Arrange
        Exception exception = new Exception();
        CommonResult actualResult = new CommonResult();
        actualResult.setCode(ErrorCode.UNKNOWN.getCode());
        actualResult.setMessage(ErrorCode.UNKNOWN.getMessage());
        // Act
        CommonResult expectedResult = globalExceptionHandler.defaultException(exception);
        // Assert
        assertEquals(expectedResult.getCode(), actualResult.getCode());
        assertEquals(expectedResult.getMessage(), actualResult.getMessage());
    }
}