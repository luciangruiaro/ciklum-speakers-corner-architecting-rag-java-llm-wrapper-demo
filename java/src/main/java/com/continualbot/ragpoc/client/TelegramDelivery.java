package com.continualbot.ragpoc.client;


import com.continualbot.ragpoc.config.TelegramConfig;
import com.continualbot.ragpoc.contacts.Conversation;
import com.continualbot.ragpoc.helper.MessageHelper;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class TelegramDelivery {

    @Autowired
    MessageHelper messageHelper;

    TelegramBot bot = new TelegramBot(TelegramConfig.TOKEN);

    // send message
    public void sendMessage(Conversation conversation, String rawMessage) throws SQLException {
        SendMessage request = new SendMessage(conversation.getChatId(), rawMessage);
        SendResponse sendResponse = bot.execute(request);
        if (sendResponse.isOk()) {
            messageHelper.printMessage(TelegramConfig.MESSAGE_SENT, sendResponse.message());
        } else {
            System.out.println("Could not send the message at this time.");
        }
    }


}
