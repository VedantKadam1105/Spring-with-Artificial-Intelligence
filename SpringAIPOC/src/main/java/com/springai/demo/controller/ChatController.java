package com.springai.demo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem("You are a helpful assistant.")
                .build();
    }

    @PostMapping
    public Map<String, String> chat(@RequestBody Map<String, String> body) {
        String reply = chatClient.prompt()
                .user(body.get("message"))
                .call()
                .content();
        return Map.of("reply", reply);
    }
}