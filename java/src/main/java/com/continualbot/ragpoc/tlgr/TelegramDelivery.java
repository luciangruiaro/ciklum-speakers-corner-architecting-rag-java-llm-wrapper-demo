package com.continualbot.ragpoc.tlgr;


import com.continualbot.ragpoc.core.Conversation;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.continualbot.ragpoc.helper.MessageHelper.printMessage;


@Service
public class TelegramDelivery {

    private static final Logger logger = LoggerFactory.getLogger(TelegramDelivery.class);

    TelegramBot telegramBot = new TelegramBot(TelegramConfig.TOKEN);

    // send message
    public void sendMessage(Conversation conversation, String rawMessage) {
        SendMessage request = new SendMessage(conversation.getChatId(), rawMessage);
        SendResponse sendResponse = telegramBot.execute(request);
        if (sendResponse.isOk()) {
            printMessage(TelegramConfig.MESSAGE_SENT, sendResponse.message());
        } else {
            logger.error("Could not send the message at this time.");
        }
    }


}
