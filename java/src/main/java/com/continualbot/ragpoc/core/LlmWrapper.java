package com.continualbot.ragpoc.core;

import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LlmWrapper {
    private final OpenAiChatClient chatClient;

    @Autowired
    public LlmWrapper(OpenAiChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public Map<String, String> generatePromptResponse(String prompt) {
        return Map.of("generation", chatClient.call(prompt));
    }

}
