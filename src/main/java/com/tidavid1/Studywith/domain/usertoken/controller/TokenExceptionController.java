package com.tidavid1.Studywith.domain.usertoken.controller;

import com.tidavid1.Studywith.domain.usertoken.exception.CAccessDeniedException;
import com.tidavid1.Studywith.domain.usertoken.exception.CAuthenticationEntryPointException;
import com.tidavid1.Studywith.global.common.response.model.CommonResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "TokenException")
@RequiredArgsConstructor
@RestController
@RequestMapping("/exception")
public class TokenExceptionController {
    @GetMapping("/entryPoint")
    public CommonResult entryPointException(){
        throw new CAuthenticationEntryPointException();
    }

    @GetMapping("/accessDenied")
    public CommonResult accessDeniedException(){
        throw new CAccessDeniedException();
    }
}
