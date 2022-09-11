package com.restapi.restApi.api.controller;

import com.restapi.restApi.api.controller.dto.user.UserResponseDto;
import com.restapi.restApi.api.controller.dto.response.Response;
import com.restapi.restApi.domain.entity.User;
import com.restapi.restApi.domain.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "1. User")
@RequiredArgsConstructor
@RestController
@RequestMapping("/sidefit")
public class UserController {

    private final UserService userService;

    @Operation(summary = "모든 회원 조회", description = "모든 회원 목록을 조회합니다.")
    @GetMapping("/users")
    public Response findAllUser() {
        List<User> findUsers = userService.findAllUser();

        List<UserResponseDto> users = findUsers.stream().map(u -> new UserResponseDto(u.getId(), u.getEmail(), u.getName()))
                .collect(Collectors.toList());

        return Response.success(users);
    }
}
