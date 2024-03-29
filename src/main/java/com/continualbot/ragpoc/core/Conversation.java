package com.continualbot.ragpoc.core;

import lombok.Getter;

@Getter
public class Conversation {
    private final String chatId;
    private final String username;

    public Conversation(String chatId, String username) {
        this.chatId = chatId;
        this.username = username;
    }


}
