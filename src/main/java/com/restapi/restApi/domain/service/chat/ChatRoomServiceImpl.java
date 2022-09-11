package com.restapi.restApi.domain.service.chat;

import com.restapi.restApi.domain.entity.ChatRoom;
import com.restapi.restApi.domain.entity.Member;
import com.restapi.restApi.domain.entity.User;
//import com.restapi.restApi.domain.repository.ChatRoomJpaRepo;
import com.restapi.restApi.domain.repository.MemberJpaRepo;
import com.restapi.restApi.domain.repository.UserJpaRepo;
import com.restapi.restApi.domain.service.dto.ChatRoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatRoomServiceImpl implements ChatRoomService {

//    private final ChatRoomJpaRepo chatRoomJpaRepo;
//    private final UserJpaRepo userJpaRepo;
//    private final MemberJpaRepo memberJpaRepo;
//
//    @Override
//    public List<ChatRoomDto> findAllChatRoom(Long userId) {
//
//        // userId를 통해 user를 찾고 ChatRoom 찾기?
//        User user = userJpaRepo.findById(userId).orElseThrow(IllegalArgumentException::new);
//
//        //TODO 채팅방 fetch join?
//        List<Member> members = memberJpaRepo.findMemberByUser(user);
//        List<ChatRoomDto> chatRoomDtos = new ArrayList<>();
//
//        for (Member member : members) {
//            Long chatroomId = member.getChatRoom().getId();
//            String title = member.getChatRoom().getTitle();
//            chatRoomDtos.add(new ChatRoomDto(chatroomId, title));
//        }
//
//        return chatRoomDtos;
//    }
//
//    @Override
//    public ChatRoomDto createChatRoom(Long userId, String title) {
//        User user = userJpaRepo.findById(userId).orElseThrow(IllegalArgumentException::new);
//        ChatRoom chatRoom = ChatRoom.builder()
//                .title(title).build();
//
//        ChatRoom savedRoom = chatRoomJpaRepo.save(chatRoom);
//        memberJpaRepo.save(Member.createMember(user, chatRoom));
//        return new ChatRoomDto(savedRoom.getId(), savedRoom.getTitle());
//    }
}
