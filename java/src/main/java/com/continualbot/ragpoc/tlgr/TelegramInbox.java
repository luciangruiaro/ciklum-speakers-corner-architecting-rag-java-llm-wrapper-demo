package com.continualbot.ragpoc.tlgr;

import com.continualbot.ragpoc.core.Agent;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TelegramInbox {

    private static final Logger logger = LoggerFactory.getLogger(TelegramInbox.class);
    private final TelegramBot telegramBot = new TelegramBot(TelegramConfig.TOKEN);
    @Autowired
    private Agent agent;

    @PostConstruct
    public void getUnreadMessages() {
        telegramBot.setUpdatesListener(updates -> {
            updates.forEach(update -> {
                if (update.message() != null) {
                    try {
                        agent.generateReply(update.message());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
