package com.continualbot.ragpoc.core;


import com.continualbot.ragpoc.tlgr.TelegramConfig;
import com.continualbot.ragpoc.tlgr.TelegramDelivery;
import com.pengrad.telegrambot.model.Message;
import org.springframework.stereotype.Component;

import static com.continualbot.ragpoc.helper.MessageHelper.printMessage;

@Component
public class Agent {

    private final TelegramDelivery telegramDelivery;

    public Agent(TelegramDelivery telegramDelivery) {
        this.telegramDelivery = telegramDelivery;
    }

    public void generateReply(Message message) {
        String answer = "Hello, I am Rag. I am here to help you. I am still learning. Please be patient with me.";
        Conversation conversation = new Conversation(message.chat().id().toString(), message.chat().username());
        printMessage(TelegramConfig.MESSAGE_RECEIVED, message);
        telegramDelivery.sendMessage(conversation, answer);
    }
}
