package com.pturo.userapp.utils;

import com.pturo.userapp.model.Messages;
import com.pturo.userapp.repository.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessagesSender {
    private MessagesRepository messagesRepository;

    @Autowired
    public MessagesSender(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    public String sendMessageToDB(String message) {
        messagesRepository.save(new Messages(message));
        return message;
    }
}
