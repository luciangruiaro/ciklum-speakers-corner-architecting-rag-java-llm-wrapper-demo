package com.continualbot.ragpoc.helper;

import com.pengrad.telegrambot.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MessageHelper {
    private static final Logger logger = LoggerFactory.getLogger(MessageHelper.class);

    private MessageHelper() {
    }

    public static void printMessage(String messageType, Message message) {
        logger.info("{} {}, {} -> {}", messageType, message.chat().id(), message.chat().username(), message.text());
    }
}
