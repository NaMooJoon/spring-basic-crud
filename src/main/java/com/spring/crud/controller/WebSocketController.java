package com.spring.crud.controller;

import com.spring.crud.dto.TbUserDto.TbUserAfterSelectDto;
import com.spring.crud.security.ExternalProperties;
import com.spring.crud.service.AuthService;
import com.spring.crud.service.TbUserService;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AuthService authService;
    private final ExternalProperties externalProperties;
    private final TbUserService tbUserService;

    public WebSocketController(AuthService authService, ExternalProperties externalProperties,
                               TbUserService tbUserService) {
        this.authService = authService;
        this.externalProperties = externalProperties;
        this.tbUserService = tbUserService;
    }

    @MessageMapping("/send")
    @SendTo("/topic/listener")
    public Map<String, Object> send(Map<String, Object> param) throws Exception {
        String accessToken = ((String) param.get("accessToken")).replace(externalProperties.getTokenPrefix(), "");
        String message = (String) param.get("message");
        String tbUserId = authService.verifyAccessToken(accessToken);
        TbUserAfterSelectDto dto = tbUserService.detail(tbUserId);

        Map<String, Object> msg = new HashMap<>();
        msg.put("sender_nick", dto.getNick());
        msg.put("message", message);
        return msg;
    }
}
