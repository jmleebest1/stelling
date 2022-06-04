package com.team1.stelling.controller;


import com.team1.stelling.domain.repository.ChatRoomRepository;
import com.team1.stelling.domain.repository.UserRepository;
import com.team1.stelling.domain.vo.ChatRoom;
import com.team1.stelling.domain.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor

public class ChatController {
    private final ChatRoomRepository chatRoomRepository;
    private  final UserRepository userRepository;

    @GetMapping("/rooms")
    public String rooms(Model model) {
        model.addAttribute("rooms", chatRoomRepository.findAllRoom());
        return "home";
    }

    @GetMapping("/rooms/{id}")
    public String room(@PathVariable String id) {
        ChatRoom room = chatRoomRepository.findRoomById(id);


        return "chatroom";
    }

    @GetMapping("/new")
    public String make(Model model) {
        ChatRoomForm form = new ChatRoomForm();
        model.addAttribute("form", form);
        return "newRoom";
    }

    @PostMapping("/room/new")
    public String makeRoom(@RequestBody String name) {

        chatRoomRepository.createChatRoom(name);


        return "chatroom";
    }

    //user목록 가져오기
    @GetMapping("/list")
    @ResponseBody
    public List<String> List(){
        List<String> list = userRepository.findAll().stream().map(UserVO::getUserNickName).collect(Collectors.toList());

        return list;
    }
}
