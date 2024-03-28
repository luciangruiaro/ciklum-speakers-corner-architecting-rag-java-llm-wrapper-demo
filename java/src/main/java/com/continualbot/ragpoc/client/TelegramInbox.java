package com.continualbot.ragpoc.client;


import com.continualbot.ragpoc.config.TelegramConfig;
import com.continualbot.ragpoc.core.Agent;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelegramInbox {

    @Autowired
    Agent agent;
    TelegramBot bot = new TelegramBot(TelegramConfig.TOKEN);


    @PostConstruct
    // check messages inbox
    public void getUnreadMessages() {
        bot.setUpdatesListener(new UpdatesListener() {
            @Override
            public int process(List<Update> updates) {
                for (Update update : updates) {
                    try {
                        agent.generateReply(update.message());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return UpdatesListener.CONFIRMED_UPDATES_ALL;
            }
        });
    }

}
