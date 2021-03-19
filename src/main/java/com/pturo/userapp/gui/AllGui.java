package com.pturo.userapp.gui;

import com.pturo.userapp.model.Messages;
import com.pturo.userapp.repository.MessagesRepository;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("all")
public class AllGui extends VerticalLayout {
    private MessagesRepository messagesRepository;

    @Autowired
    public AllGui(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;

        List<Messages> messages = messagesRepository.findAll();
        messages.stream().forEach(element -> {
            add(element.getMessage());
        });
    }
}
