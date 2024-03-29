package com.continualbot.ragpoc.core;

import lombok.Getter;

@Getter
public class Conversation {
    private String chatId;
    private String username;

    public Conversation(String chatId, String username) {
        this.chatId = chatId;
        this.username = username;
    }


}
