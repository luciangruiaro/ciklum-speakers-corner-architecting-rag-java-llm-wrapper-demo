package com.continualbot.ragpoc.core;

import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LlmWrapper {git config --list --local

    private final OpenAiChatClient chatClient;

    @Autowired
    public LlmWrapper(OpenAiChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String generatePromptResponse(String prompt) {
        String context = "";
//                context += " Please answer using sophisticated words, as you would when writing a scientific paper.";
                context += " Please check my words as if you're explaining to a 7-year-old.";
        return Map.of("generation", chatClient.call(prompt + context)).get("generation");
    }

}
