package com.restapi.restApi.api.controller.exception;

import com.restapi.restApi.advice.exception.CAccessDeniedException;
import com.restapi.restApi.advice.exception.CAuthenticationEntryPointException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/exception")
public class ExceptionController {

    // 정상적으로 Jwt가 제대로 오지 않은 경우
    @GetMapping("/entryPoint")
    public void entrypointException() {
        throw new CAuthenticationEntryPointException();
    }

    // 정상적인 Jwt가 왔지만 권한이 다른경우 
    @GetMapping("/accessDenied")
    public void accessDeniedException() {
        throw new CAccessDeniedException();
    }

    @GetMapping("/test")
    public void test() {
        throw new IllegalStateException("에러 테스트");
    }
}
