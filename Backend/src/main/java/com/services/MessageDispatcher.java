package com.services;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageDispatcher {
    private final SimpMessagingTemplate template;

    public MessageDispatcher(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void sendToClient(@DestinationVariable String client, String message) {
        this.template.convertAndSend("/queue/distributor." + client, message);
    }
}
