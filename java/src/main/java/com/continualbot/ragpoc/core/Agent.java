package com.continualbot.ragpoc.core;


import com.continualbot.ragpoc.tlgr.TelegramDelivery;
import com.continualbot.ragpoc.tlgr.TelegramConfig;
import com.pengrad.telegrambot.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.continualbot.ragpoc.helper.MessageHelper.printMessage;

@Component
public class Agent {

    @Autowired
    private TelegramDelivery telegramDelivery;


    public void generateReply(Message message) {
        String answer = KnowledgeBase.DEFAULT_ANSWER;
        Conversation conversation = new Conversation(message.chat().id().toString(), message.chat().username());
        printMessage(TelegramConfig.MESSAGE_RECEIVED, message);
        telegramDelivery.sendMessage(conversation, answer);
    }
}
