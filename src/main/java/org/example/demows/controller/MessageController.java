package org.example.demows.controller;

import org.example.demows.dto.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Logan
 * @since 5/23/25 18:04
 **/
@RestController("publicChatController")
public class MessageController {
    @MessageMapping("broadcast.chat")
    @SendTo("/groupChat/all")
    public ChatMessage sendToGroupChat(@Payload ChatMessage message) {
        System.out.println("message = " + message);
        return message;
    }

    @MessageMapping("broadcast.join")
    @SendTo("/groupChat/all")
    public ChatMessage join(@Payload ChatMessage message) {
        message.setContent(message.getSender() + " joined.");
        System.out.println("joined," + message);
        return message;
    }
    @RequestMapping("/hello")
    public String hello() {
        System.out.println("hello");
        return "hello";
    }
}
