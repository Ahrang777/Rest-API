package com.restapi.restApi.domain.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDto {

    private Long chatroomId;
    private Long userId;
    private String sender;
    private String message;
}
