package com.chat.chatapp.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

//TO connect with server :- /server1
//To send message :-  /app/message
//                     {message body}
//To subscribe so that we can receive messages :- /topic/return-to

@Configuration
@EnableWebSocketMessageBroker
public class Config implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
         registry.enableSimpleBroker("/topic"); //subscribe
         registry.setApplicationDestinationPrefixes("/app"); //sending message
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //before sneding/receiving message we have to create the connectivity using this url
         registry.addEndpoint("/server1").withSockJS(); //to connect server
    }
}
