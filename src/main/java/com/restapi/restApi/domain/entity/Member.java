package com.restapi.restApi.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "chatroom_id")
//    private ChatRoom chatRoom;
//
//    public static Member createMember(User user, ChatRoom chatRoom) {
//        Member member = new Member();
//        member.user = user;
//        member.chatRoom = chatRoom;
//        return member;
//    }
}
