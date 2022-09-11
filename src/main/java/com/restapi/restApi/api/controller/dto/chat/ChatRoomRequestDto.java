package com.restapi.restApi.api.controller.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomRequestDto {

    private String title;
    private Long userId;
}
