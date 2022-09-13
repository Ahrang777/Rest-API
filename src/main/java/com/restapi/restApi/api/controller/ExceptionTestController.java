package com.restapi.restApi.api.controller;

import com.restapi.restApi.api.controller.dto.response.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionTestController {

    @GetMapping("/api/exception")
    public Response exception() throws Exception {
        throw new Exception();
    }
}
