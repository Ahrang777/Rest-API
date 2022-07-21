package com.restapi.restApi.api.controller.dto.user;

import com.restapi.restApi.domain.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    private String email;
    private String name;
    private String nickname;

    public User toEntity() {
        return User.builder()
                .email(email)
                .name(name)
                .nickname(nickname)
                .build();
    }
}
