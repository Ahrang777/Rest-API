package com.restapi.restApi.api.controller.chat;

import com.restapi.restApi.api.controller.dto.chat.ChatMessageDto;
import com.restapi.restApi.domain.entity.ChatMessage;
import com.restapi.restApi.domain.service.chat.ChatMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

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

//    private final SimpMessagingTemplate template;
//    private final ChatMessageService chatMessageService;
//
//    // TODO 채팅방 처음 입장하는 경우, 기존 방 다시 들어가는 경우(채팅방 안나간 상태에서 채팅방 리스트 남아있고 그 방에 들어간 경우)
//    // TODO 채팅방 다시 들어갔을때 이전 메시지 출력하는 것
//    @Operation(summary = "채팅방 처음 입장", description = "처음 채팅방에 입장한 경우")
//    @MessageMapping("/chat/join")
//    public void join(
//            @Parameter(description = "채팅 메시지 DTO", required = true)
//            @RequestBody ChatMessageDto message) {
//        log.info("입장 msg {}", message);
//
//        message.setMessage(message.getSender() + "님이 입장하셨습니다.");
//
//        // 저장
//        chatMessageService.saveChatMessage(message.getUserId(), message.getChatroomId(), message.getMessage());
//
//        template.convertAndSend("/subscribe/chat/room/" + message.getChatroomId(), message);
//    }
//    /*public void join(ChatMessage message) {
//        log.info("입장 msg {}", message);
//
//        message.setMessage(message.getSender() + "님이 입장하셨습니다.");
//
//        template.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(), message);
//    }*/
//
//    @Operation(summary = "채팅 메시지 전송", description = "현재 채팅방에 있는 사람들에게 채팅 메시지 전송")
//    @MessageMapping("/chat/message")
//    public void message(
//            @Parameter(description = "채팅 메시지 DTO", required = true)
//            @RequestBody ChatMessageDto message) {
//
//        log.info("채팅 msg {}", message);
//
//        // 저장
//        chatMessageService.saveChatMessage(message.getUserId(), message.getChatroomId(), message.getMessage());
//
//        template.convertAndSend("/subscribe/chat/room/" + message.getChatroomId(), message);
//    }
//    /*public void message(ChatMessage message) {
//
//        log.info("채팅 msg {}", message);
//        template.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(), message);
//    }*/
}
