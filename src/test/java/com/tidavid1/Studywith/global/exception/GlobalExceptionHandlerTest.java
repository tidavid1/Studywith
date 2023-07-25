package com.tidavid1.Studywith.global.exception;

import com.tidavid1.Studywith.global.common.response.model.CommonResult;
import com.tidavid1.Studywith.global.common.response.service.ResponseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    ResponseService responseService;
    @Mock
    MessageSource messageSource;
    @InjectMocks
    GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setup(){
        responseService = new ResponseService();
        globalExceptionHandler = new GlobalExceptionHandler(responseService, messageSource);
    }
    @DisplayName("Test DefaultException")
    @Test
    void testDefaultException(){
        // Arrange
        Exception exception = new Exception();
        CommonResult actualResult = new CommonResult();
        actualResult.setCode(999);
        // Act
        CommonResult expectedResult = globalExceptionHandler.defaultException(exception);
        // Assert
        assertEquals(expectedResult.getCode(), actualResult.getCode());
        assertEquals(expectedResult.getMessage(), actualResult.getMessage());
    }
}