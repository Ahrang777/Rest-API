package com.restapi.restApi.domain.entity;

import lombok.Getter;

import javax.persistence.Entity;

@Getter
@Entity
public class Member {

    private User user;
    private ChatRoom chatRoom;
}
