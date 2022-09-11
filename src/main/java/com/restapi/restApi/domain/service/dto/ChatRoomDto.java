package com.restapi.restApi.domain.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomDto {

    private Long chatroomId;
    private String title;
}
