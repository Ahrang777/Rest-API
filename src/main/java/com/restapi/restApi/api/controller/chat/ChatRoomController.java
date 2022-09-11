package com.restapi.restApi.api.controller.chat;

import com.restapi.restApi.api.controller.dto.chat.ChatRoomRequestDto;
import com.restapi.restApi.api.controller.dto.response.Response;
import com.restapi.restApi.domain.entity.ChatRoom;
import com.restapi.restApi.domain.repository.ChatRoomRepository;
import com.restapi.restApi.domain.service.chat.ChatRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.concurrent.atomic.AtomicInteger;

@Tag(name = "2.ChatRoom")
//@RestController
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


//    private final ChatRoomService chatRoomService;
//
//    // 추후 slice 형태로 변경
//    // TODO 추후 Slice 형태로 변경
//    // TODO 경로를 /chat/users/{id} 말고 다른거 수정할지
//    @Operation(summary = "채팅방 목록 조회", description = "내가 입장한 채팅방의 전체 목록을 조회합니다.")
//    @GetMapping("/users/{id}")
//    public Response findAllRoom(
//            @Parameter(description = "user PK", required = true)
//            @PathVariable Long id) {
//
//        return Response.success(chatRoomService.findAllChatRoom(id));
//    }
//
//    @Operation(summary = "채팅방 생성", description = "새로운 채팅방 만들기")
//    @PostMapping("/rooms")
//    public Response createChatRoom(
//            @Parameter(description = "채팅방 생성 DTO", required = true)
//            @RequestBody ChatRoomRequestDto chatRoomRequestDto) {
//
//        return Response.success(chatRoomService.createChatRoom(chatRoomRequestDto.getUserId(), chatRoomRequestDto.getTitle()));
//    }

    // 채팅방에 사람 추가

    // 채팅방 나가기



    /*private final AtomicInteger seq = new AtomicInteger(0);
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
    }*/

}
