package com.spring.crud.controller;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.spring.crud.security.ExternalProperties;
import com.spring.crud.security.JwtTokenDto;
import com.spring.crud.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "0. 권한 API 안내",
        description = "권한 기능 정의한 RestController.")
@RequestMapping("/api/auth")
@RestController
public class AuthRestController {

    private final AuthService authService;
    private final ExternalProperties externalProperties;

    public AuthRestController(AuthService authService, ExternalProperties externalProperties) {
        this.authService = authService;
        this.externalProperties = externalProperties;
    }

    @Operation(summary = "Access Token 발급을 위한 컨트롤러",
            description = "Refresh Token이 헤더에 있고 해당 토큰이 유효하다면 Access Token 발급. <br />"
                    + "@param RefreshToken(String) =\\> 헤더에 담을것! <br />"
                    + "@return String Authorization(header), ResponseEntity\\<String\\> <br />"
                    + "@exception JWTVerificationException(Invalid Refresh Token) <br />"
    )
    @PostMapping("/access-token")
    public ResponseEntity<String> issueAccessToken(HttpServletRequest request, HttpServletResponse response) {
        ResponseEntity<String> responseEntity = null;
        String refreshToken = request.getHeader(externalProperties.getRefreshKey());
        System.out.println("refreshToken = " + refreshToken);
        if (refreshToken == null || refreshToken.isEmpty() || !refreshToken.startsWith(externalProperties.getTokenPrefix())) {
            // 쿠키에 Refresh token이 없으면 return 401(UNAUTHORIZED)
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            return responseEntity;
        }
        refreshToken = refreshToken.replace(externalProperties.getTokenPrefix(), "");
        try {
            // 쿠키에 Refresh Token이 있으면 검증 후 Access token 발급.
            JwtTokenDto jwtTokenDto = authService.issueAccessToken(refreshToken);
            // Access token은 response header에 set.
            response.addHeader(externalProperties.getAccessKey(),
                    externalProperties.getTokenPrefix() + jwtTokenDto.getAccessToken());
            // return 200(OK)
            responseEntity = ResponseEntity.status(HttpStatus.OK).build();
        } catch (JWTVerificationException e) { // Invalid refresh token
//            e.printStackTrace();
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return responseEntity;
    }
}
