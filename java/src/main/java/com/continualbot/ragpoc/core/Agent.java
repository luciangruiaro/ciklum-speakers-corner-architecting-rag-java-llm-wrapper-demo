package com.continualbot.ragpoc.core;


import com.continualbot.ragpoc.client.TelegramDelivery;
import com.continualbot.ragpoc.config.TelegramConfig;
import com.continualbot.ragpoc.contacts.Conversation;
import com.continualbot.ragpoc.core.knowledge.KnowledgeBase;
import com.continualbot.ragpoc.core.lifecycle.DecisionEngine;
import com.continualbot.ragpoc.helper.MessageHelper;
import com.continualbot.ragpoc.helper.TextUtils;
import com.pengrad.telegrambot.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Agent {

    @Autowired
    TelegramDelivery telegramDelivery;
    @Autowired
    DecisionEngine decisionEngine;
    @Autowired
    MessageHelper messageHelper;
    @Autowired
    KnowledgeBase knowledgeBase;
    @Autowired
    TextUtils textUtils;
    String answer;


    public void generateReply(Message message) throws Exception {

        String conversationState = decisionEngine.decideCurrentCoversationState(message.text()).getName();

        Conversation conversation =
                new Conversation(String.valueOf(message.chat().id()), message.chat().username(), conversationState);

        // go to branch
        switch (conversationState) {
            case "Greetings" -> answer = KnowledgeBase.HELLO_MESSAGE;
            case "Question" -> {
                answer = knowledgeBase.getDataFromSolr(textUtils.extractWordsAfterAbout(message.text()));
                if (answer.isEmpty()) {
                    answer = KnowledgeBase.DEFAULT_IDK;
                    telegramDelivery.sendMessage(conversation, answer);
                    answer = knowledgeBase.searchForMoreKnowledge(message.text());
                }
            }
//            case "ai" -> answer = new Completion(aiClient.generate(message.text())).getCompletion();
            default -> answer = KnowledgeBase.DEFAULT_ANSWER;
        }

        // print to console
        messageHelper.printMessage(TelegramConfig.MESSAGE_RECEIVED, message);

        // send the message
        telegramDelivery.sendMessage(conversation, answer);
    }

}
