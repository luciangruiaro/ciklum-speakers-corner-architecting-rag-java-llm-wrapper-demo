package com.continualbot.ragpoc.core;

import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class LlmWrapper {
    public static final String pyLlmCall = "http://localhost:5500/query";
    private final OpenAiChatClient chatClient;

    @Autowired
    public LlmWrapper(OpenAiChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String generatePromptResponse(String prompt) {
        return Map.of("generation", chatClient.call(prompt)).get("generation");
    }

    public String queryPy(String prompt) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestBody = Map.of("prompt", prompt);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

        Map<String, String> response = restTemplate.postForObject(pyLlmCall, request, Map.class);
        return response != null ? response.get("completion") : null;
    }

}
