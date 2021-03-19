package com.pturo.userapp.gui;

import com.pturo.userapp.model.Messages;
import com.pturo.userapp.repository.MessagesRepository;
import com.pturo.userapp.utils.MessagesSender;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("admin")
public class AdminGui extends VerticalLayout {
    private MessagesSender messagesSender;
    private MessagesRepository messagesRepository;

    @Autowired
    public AdminGui(MessagesSender messagesSender, MessagesRepository messagesRepository) {
        this.messagesSender = messagesSender;
        this.messagesRepository = messagesRepository;

        Label msgLabel = new Label();
        TextField msgField = new TextField();
        msgField.setLabel("Message input field");
        msgField.setPlaceholder("Input your message here...");

        Button confirm = new Button("SEND");
        confirm.addClickListener(clickEvent -> {
            String msgToSend = messagesSender.sendMessageToDB(msgField.getValue());
            msgLabel.setText("Message sent successfully!");
            add(msgLabel);
        });

        // Display message which was sent
        List<Messages> messages = messagesRepository.findAll();
        messages.stream().forEach(element -> {
            add("User: " + element.getMessage());
        });

        add(msgField);
        add(confirm);
    }
}
