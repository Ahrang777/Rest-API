package com.restapi.restApi.api.controller.chat;

import com.restapi.restApi.domain.entity.ChatRoom;
import com.restapi.restApi.domain.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatRoomController {

    private final AtomicInteger seq = new AtomicInteger(0);
    private final ChatRoomRepository repository;

    // 전체 채팅방 목록
    @GetMapping("/rooms")
    public String rooms(Model model) {
        model.addAttribute("rooms", repository.findAll());
        return "chat/rooms";
    }

    // 채팅방 생성
    @PostMapping("/rooms")
    public String createChatRoom(@RequestParam String name, RedirectAttributes redirectAttributes) {
        ChatRoom chatRoom = repository.createChatRoom(name);

        redirectAttributes.addFlashAttribute("roomName", chatRoom.getName());
        return "redirect:/chat/rooms";
    }

    // 특정 채팅방 입장
    @GetMapping("/rooms/{roomId}")
    public String room(@PathVariable String roomId, Model model) {
        ChatRoom room = repository.findChatRoomByRoomId(roomId);
        model.addAttribute("room", room);
        model.addAttribute("member", "member" + seq.incrementAndGet());
        return "chat/room-detail";
    }
}
