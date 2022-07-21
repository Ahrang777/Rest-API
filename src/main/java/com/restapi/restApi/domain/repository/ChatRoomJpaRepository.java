package com.restapi.restApi.domain.repository;

import com.restapi.restApi.domain.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomJpaRepository extends JpaRepository<ChatRoom, Long> {


}
