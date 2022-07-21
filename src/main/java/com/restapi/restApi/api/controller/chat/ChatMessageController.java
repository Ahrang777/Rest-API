package com.restapi.restApi.api.controller.chat;

import com.restapi.restApi.domain.entity.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatMessageController {

    private final SimpMessagingTemplate template;

    @MessageMapping("/chat/join")
    public void join(ChatMessage message) {
        log.info("입장 msg {}", message);

        message.setMessage(message.getSender() + "님이 입장하셨습니다.");

        template.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(), message);
    }

    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {

        log.info("채팅 msg {}", message);
        template.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(), message);
    }
}
