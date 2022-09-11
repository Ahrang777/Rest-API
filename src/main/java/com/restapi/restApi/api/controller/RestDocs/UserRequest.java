package com.restapi.restApi.api.controller.RestDocs;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String name;
    private int age;
}
