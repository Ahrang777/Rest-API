package com.restapi.restApi.api.controller.RestDocs;

import org.springframework.stereotype.Service;

@Service
public class RestDocsService {

    public UserResponse convert(UserRequest request) {
        return new UserResponse(request.getName());
    }
}
