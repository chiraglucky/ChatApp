package com.chat.chatapp.Controllers;

import com.chat.chatapp.Models.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    //to get the message and send to all other participants
    @MessageMapping("/message") //client send message to this url
    @SendTo("/topic/return-to") //send message to only those client who subscribed to this url
    public Message getContent(@RequestBody Message message) {
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return message;
    }

}
