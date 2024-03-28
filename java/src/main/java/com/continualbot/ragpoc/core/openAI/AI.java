package com.continualbot.ragpoc.core.openAI;

import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class AI {

//    private final OpenAiChatClient chatClient;
//
//    public AI(OpenAiChatClient chatClient) {
//        this.chatClient = chatClient;
//    }


    public String getLlmCompletion(String prompt) {
//        ChatResponse response = chatClient.call(
//                new Prompt(
//                        "Generate the names of 5 famous pirates.",
//                        OpenAiChatOptions.builder()
//                                .withModel("gpt-4-32k")
//                                .withTemperature(0.4f)
//                                .build()
//                ));

        return "asa";
//        return Map.of("generation", response);
    }
}
