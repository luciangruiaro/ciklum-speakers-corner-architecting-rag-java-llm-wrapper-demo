package com.continualbot.ragpoc.core;

import lombok.Getter;

@Getter
public class Conversation {
    public String chatId;
    public String username;


    public Conversation(String chatId, String username) {
        this.chatId = chatId;
        this.username = username;
    }


}
