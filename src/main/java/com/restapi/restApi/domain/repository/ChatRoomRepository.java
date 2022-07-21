package com.restapi.restApi.domain.repository;

import com.restapi.restApi.domain.entity.ChatRoom;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ChatRoomRepository {

    private Map<String, ChatRoom> chatRoomMap;

    // 테스트용 채팅방
    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
        String roomId = "test";
        ChatRoom chatRoom = createChatRoom(roomId);
        chatRoomMap.put(roomId, chatRoom);
    }

    // 현재 roomId 는 room name
    public ChatRoom findChatRoomByRoomId(String roomId) {
        return chatRoomMap.get(roomId);
    }

    public List<ChatRoom> findAll() {
        // 채팅방 생성 순서 최근 순으로 반환 >> Collections.reverse로 최근 생성된게 먼저 보이게
        List<ChatRoom> rooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(rooms);

        return rooms;
    }

    public ChatRoom createChatRoom(String name) {
        ChatRoom chatRoom = ChatRoom.create(name);
        chatRoomMap.put(chatRoom.getName(), chatRoom);
        return chatRoom;
    }

    /*
    private final Map<String, ChatRoom> chatRoomMap;
    @Getter
    private final Collection<ChatRoom> chatRooms;
    public ChatRoomRepository() {
        chatRoomMap = Collections.unmodifiableMap(
                Stream.of(ChatRoom.create("1번방"), ChatRoom.create("2번방"), ChatRoom.create("3번방"))
                      .collect(Collectors.toMap(ChatRoom::getId, Function.identity())));
        chatRooms = Collections.unmodifiableCollection(chatRoomMap.values());
    }
    public ChatRoom getChatRoom(String id) {
        return chatRoomMap.get(id);
    }
    public void remove(WebSocketSession session) {
        this.chatRooms.parallelStream().forEach(chatRoom -> chatRoom.remove(session));
    }
     */
}
