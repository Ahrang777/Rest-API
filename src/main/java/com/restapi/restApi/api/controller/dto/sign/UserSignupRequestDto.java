package com.restapi.restApi.api.controller.dto.sign;

import com.restapi.restApi.domain.service.dto.UserSignupDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupRequestDto {

    @NotEmpty
    @Email
    private String email;

    private String password;

    private String name;
    private String nickname;
//    private String provider;

    public UserSignupDto toUserSignupDto() {
        return UserSignupDto.builder()
                .email(email)
                .password(password)
                .name(name)
                .nickname(nickname)
                .build();
    }

    /*public User toEntity(PasswordEncoder passwordEncoder) {
        return User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .name(name)
                .roles(Collections.singletonList("USER"))
                .build();
    }

    public User toEntity() {
        return User.builder()
                .email(email)
                .nickname(nickname)
                .name(name)
                .provider(provider)
                .roles(Collections.singletonList(RoleType.USER))
                .build();
    }*/
}
