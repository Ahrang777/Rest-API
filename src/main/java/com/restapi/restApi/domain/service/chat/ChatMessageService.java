package com.restapi.restApi.domain.service.chat;

import com.restapi.restApi.domain.entity.ChatMessage;
import com.restapi.restApi.domain.entity.ChatRoom;
import com.restapi.restApi.domain.entity.User;
//import com.restapi.restApi.domain.repository.ChatMessageJpaRepo;
//import com.restapi.restApi.domain.repository.ChatRoomJpaRepo;
import com.restapi.restApi.domain.repository.UserJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatMessageService {

//    private final ChatMessageJpaRepo chatMessageJpaRepo;
//    private final UserJpaRepo userJpaRepo;
//    private final ChatRoomJpaRepo chatRoomJpaRepo;
//
//    public Long saveChatMessage(Long userId, Long chatroomId, String message) {
//        User user = userJpaRepo.findById(userId).orElseThrow(IllegalArgumentException::new);
//        ChatRoom chatRoom = chatRoomJpaRepo.findById(chatroomId).orElseThrow(IllegalArgumentException::new);
//        ChatMessage chatMessage = ChatMessage.createChatMessage(user, chatRoom, message);
//        ChatMessage savedChatMessage = chatMessageJpaRepo.save(chatMessage);
//        return savedChatMessage.getId();
//    }
}
