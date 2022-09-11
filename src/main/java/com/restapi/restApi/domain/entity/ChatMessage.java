package com.restapi.restApi.domain.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

//@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChatMessage {

    private String chatRoomId;
    private String sender;
    private String message;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "chatmessage_id")
//    private Long id;
//
//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "chatroom_id")
//    private ChatRoom chatRoom;
//
//    private String message;
//
//    public static ChatMessage createChatMessage(User user, ChatRoom chatRoom, String message) {
//        ChatMessage chatMessage = new ChatMessage();
//        chatMessage.user = user;
//        chatMessage.chatRoom = chatRoom;
//        chatMessage.message = message;
//        return chatMessage;
//    }
}
