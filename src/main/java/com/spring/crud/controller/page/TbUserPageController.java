package com.spring.crud.controller.page;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.spring.crud.dto.TbUserDto.TbUserCreateDto;
import com.spring.crud.security.JwtTokenDto;
import com.spring.crud.service.TbUserService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Collections;
import jdk.jshell.spi.ExecutionControl.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/user")
@Controller
public class TbUserPageController {

    @Value("{google.login.client_id}")
    private String google_client_id;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final TbUserService tbUserService;

    public TbUserPageController(TbUserService tbUserService) {
        this.tbUserService = tbUserService;
    }

    @GetMapping("/{page}")
    public String user(@PathVariable("page") String page) {
        return "/user/" + page;
    }

    @GetMapping("/login/naver")
    public String naverLogin(HttpServletRequest request) throws Exception {
        return "/user/naver";
    }

    @PostMapping("/login/google")
    public String googleLogin(@RequestParam String credential, Model model) {
        logger.info("google login");
        logger.info("Credential: " + credential);

        HttpTransport transport = null;
        transport = new NetHttpTransport();

        JsonFactory jsonFactory = new GsonFactory();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                // Specify the CLIENT_ID of the app that access the backend
                .setAudience(Collections.singletonList(google_client_id))
                // if multiple clients access the backend
                //.setAudience(Arrays.asList(CLIENT_ID1, CLIENT_ID2, CLIENT_ID3))
                .build();

        GoogleIdToken idToken = null;
        try {
            idToken = verifier.verify(credential);
        } catch (Exception e) {
        }
        if (idToken != null) {
            Payload payload = idToken.getPayload();
            // print user identifier
            String userId = payload.getSubject();
            logger.info("payload ID: " + payload);
            String username = (String) payload.get("email");
            String password = (String) payload.get("sub");
            JwtTokenDto jwtTokenDto = tbUserService.sns(TbUserCreateDto.builder()
                    .username(username)
                    .password(password)
                    .build());
            model.addAttribute("token", jwtTokenDto);
        } else {
            System.err.println("Invalid ID token.");
        }
        return "/user/google";
    }


}
