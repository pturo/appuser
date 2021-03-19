package com.pturo.userapp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class MessageController {
    @GetMapping("/userTest")
    public String userMessage() {
        return "User Messages!";
    }

    @GetMapping("/adminTest")
    public String adminMessage() {
        return "Admin Messages!";
    }

    @GetMapping("/allTest")
    public String messagesToAll() {
        return "Messages to All!";
    }
}
