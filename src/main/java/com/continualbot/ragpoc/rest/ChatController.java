package com.continualbot.ragpoc.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);


    @GetMapping("/rag/ask")
    public String generate(@RequestParam(value = "inputMessage") String inputMessage) {
        logger.info("inputMessage: {}", inputMessage);
        return "Hello, I am Rag. I am here to help you. I am still learning. Please be patient with me.";
    }

}