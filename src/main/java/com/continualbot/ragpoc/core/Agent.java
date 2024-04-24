package com.continualbot.ragpoc.core;


import com.continualbot.ragpoc.knowledge.KnowledgeBase;
import com.continualbot.ragpoc.tlgr.TelegramConfig;
import com.continualbot.ragpoc.tlgr.TelegramDelivery;
import com.pengrad.telegrambot.model.Message;
import org.springframework.stereotype.Component;

import static com.continualbot.ragpoc.helper.MessageHelper.printMessage;

@Component
public class Agent {

    private final TelegramDelivery telegramDelivery;
    private final LlmWrapper llmWrapper;

    public Agent(TelegramDelivery telegramDelivery, LlmWrapper llmWrapper) {
        this.telegramDelivery = telegramDelivery;
        this.llmWrapper = llmWrapper;
    }

    public void generateReply(Message message) {
//                String answer = KnowledgeBase.DEFAULT_ANSWER;
        String answer = llmWrapper.generatePromptResponse(message.text());
        Conversation conversation = new Conversation(message.chat().id().toString(), message.chat().username());
        printMessage(TelegramConfig.MESSAGE_RECEIVED, message);
        telegramDelivery.sendMessage(conversation, answer);
    }
}
