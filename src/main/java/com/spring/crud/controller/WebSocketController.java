package com.spring.crud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @MessageMapping("/send")
    @SendTo("/topic/listener")
    public String send(String message) throws Exception {
        logger.info("Socket msg: " + message);
        return message;
    }
}
