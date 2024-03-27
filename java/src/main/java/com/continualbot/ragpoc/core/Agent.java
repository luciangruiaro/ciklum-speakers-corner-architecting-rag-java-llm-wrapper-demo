package com.continualbot.ragpoc.core;

import com.continualbot.ragpoc.client.TelegramDelivery;
import com.continualbot.ragpoc.model.Conversation;
import com.pengrad.telegrambot.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class Agent {

    @Autowired
    TelegramDelivery telegramDelivery;

    @Autowired
    Conversation conversation;

    String answer;

    public void generateReply(Message message) throws Exception {
        // send the message
        telegramDelivery.sendMessage(conversation, answer);
    }

}
