package com.restapi.restApi.api.controller.security;

import com.restapi.restApi.api.controller.dto.jwt.TokenRequestDto;
import com.restapi.restApi.api.controller.dto.sign.UserLoginRequestDto;
import com.restapi.restApi.api.controller.dto.sign.UserSignupRequestDto;
import com.restapi.restApi.api.controller.dto.response.Response;
import com.restapi.restApi.domain.service.dto.TokenDto;
import com.restapi.restApi.domain.service.security.SignService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class SignController {

    private final SignService signService;

    @PostMapping("/login")
    public Response login(
            @RequestBody UserLoginRequestDto userLoginRequestDto) {

        TokenDto tokenDto = signService.login(userLoginRequestDto.getEmail(), userLoginRequestDto.getPassword());
        return Response.success(tokenDto);
    }

    /*@Operation(summary = "로그아웃", description = "로그아웃 처리")
    @PostMapping("/logout")
    public Response logout() {
        // 로그아웃 처리 >> Refresh Token DB에서 Refresh Token 삭제
        signService.logout();
    }*/


    @PostMapping("/signup")
    public Response signup(
            @RequestBody UserSignupRequestDto userSignupRequestDto) {

        Long userId = signService.signup(userSignupRequestDto.toUserSignupDto());
        return Response.success(userId);
    }

    @PostMapping("/reissue")
    public Response reissue(
            @RequestBody TokenRequestDto tokenRequestDto) {

        return Response.success(signService.reissue(tokenRequestDto.getAccessToken(), tokenRequestDto.getRefreshToken()));
    }







}
