package com.continualbot.ragpoc.rest;

import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ChatController {

    private final OpenAiChatClient chatClient;

    @Autowired
    public ChatController(OpenAiChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/rag/prompt")
    public Map generate(@RequestParam(value = "prompt", defaultValue = "Tell me something about Romania.") String prompt) {
        return Map.of("generation", chatClient.call(prompt));
    }

}