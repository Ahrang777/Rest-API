package com.restapi.restApi.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatroom_id")
    private Long id;*/

    private String roomId;
    private String name;

    public static ChatRoom create(@NonNull String name) {
        ChatRoom created = new ChatRoom();
//        created.id = UUID.randomUUID().toString();
//        created.roomId = name;  // Long id 로?
        created.name = name;
        return created;
    }
}
