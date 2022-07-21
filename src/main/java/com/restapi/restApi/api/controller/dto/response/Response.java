package com.restapi.restApi.api.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor(access= AccessLevel.PRIVATE)
@Getter
public class Response {

    @Schema(description = "응답 성공 여부: T/F")
    private boolean success;

    @Schema(description = "응답 코드(0: 정상, 특정 코드: 비정상)")
    private int code;

    @Schema(description = "응답 메시지")
    private Result result;

    public static Response success() {
        return new Response(true, 0, null);
    }

    public static <T> Response success(T data) {
        return new Response(true, 0, new Success<>(data));
    }

    public static Response failure(int code, String msg) {
        return new Response(false, code, new Failure(msg));
    }
}
