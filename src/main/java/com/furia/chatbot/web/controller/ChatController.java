package com.furia.chatbot.web.controller;


import com.furia.chatbot.application.service.ChatService;
import com.furia.chatbot.web.dto.UserMessageRequest;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<String> chat(@RequestBody UserMessageRequest request)
    {
        String response = chatService.handleUserMessage(request.message());
        return ResponseEntity.ok(response);
    }

}
