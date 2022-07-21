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

@Tag(name = "1.SignUp/SignIn")
@RequiredArgsConstructor
@RestController
@RequestMapping("/sidefit")
public class SignController {

    private final SignService signService;

    @Operation(summary = "이메일 로그인", description = "이메일로 로그인을 합니다.")
    @PostMapping("/login")
    public Response login(
            @Parameter(description = "이메일 로그인 요청 DTO", required = true)
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


    @Operation(summary = "이메일 회원가입", description = "이메일로 회원가입을 합니다.")
    @PostMapping("/signup")
    public Response signup(
            @Parameter(description = "이메일 회원가입 요청 DTO", required = true)
            @RequestBody UserSignupRequestDto userSignupRequestDto) {

        Long userId = signService.signup(userSignupRequestDto.toUserSignupDto());
        return Response.success(userId);
    }

    @Operation(
            summary = "엑세스, 리프레시 토큰 재발급",
            description = "엑세스 토큰 만료시 회원 검증 후 리프레쉬 토큰을 검증해서 액세스 토큰과 리프레시 토큰을 재발급합니다.")
    @PostMapping("/reissue")
    public Response reissue(
            @Parameter(description = "토큰 재발급 요청 DTO", required = true)
            @RequestBody TokenRequestDto tokenRequestDto) {

        return Response.success(signService.reissue(tokenRequestDto.getAccessToken(), tokenRequestDto.getRefreshToken()));
    }







}
