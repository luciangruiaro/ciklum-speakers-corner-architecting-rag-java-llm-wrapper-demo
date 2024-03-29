package com.continualbot.ragpoc.tlgr;

import com.continualbot.ragpoc.core.Agent;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;


@Service
public class TelegramInbox {

    private final TelegramBot telegramBot = new TelegramBot(TelegramConfig.TOKEN);

    private final Agent agent;

    public TelegramInbox(Agent agent) {
        this.agent = agent;
    }

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
