package com.restapi.restApi.api.controller.RestDocs;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RestDocsController {
    private final RestDocsService userService;

    @PostMapping("/user")
    public UserResponse getName(@RequestBody UserRequest userRequestDto) {
        UserResponse resopnse = userService.convert(userRequestDto);
        return resopnse;
    }
}
