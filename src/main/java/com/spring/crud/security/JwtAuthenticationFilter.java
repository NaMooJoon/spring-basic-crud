package com.spring.crud.security;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.crud.dto.TbUserDto.TbUserLoginDto;
import com.spring.crud.service.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.Transactional;

/**
 * UsernamePasswordAuthenticationFilter
 * 유저가 로그린 창에서 로그인 시도를 할 때 보내지는 아이디 패스워드 데이터(form)를 가져온 후 인증을 위한 토큰을 생성 후 인증을 다른 쪽에 위임하는 역할
 */

@Transactional
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;
    private final AuthService authService;
    private final ExternalProperties externalProperties;

    /**
     * 로그인하려는 사용자의 자격을 확인해 토큰을 발급하는 함수
     * "/api/login"으로 들어오는 요청에 의해 실행된다. (SecurityConfiguration 참조)
     * 생성된 Authentication의 SecurityContextHolder에 등록되어 권한처리가 가능하게 된다.
     */
    @Transactional
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Authentication authentication = null;
        TbUserLoginDto tbUserLoginDto = null;
        try {
            tbUserLoginDto = objectMapper.readValue(request.getInputStream(), TbUserLoginDto.class);
        } catch (IOException e) {
            System.err.println("1. Login attemptAuthentication: Not Enough Parameters");
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }

        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    tbUserLoginDto.getUsername(), tbUserLoginDto.getPassword());
            authentication = authenticationManager.authenticate(authenticationToken);
            // Login success
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            System.out.println("username: " + principalDetails.getTbUser().getUsername());
            System.out.println("password: " + principalDetails.getTbUser().getPassword());
        } catch (AuthenticationException e) {
            System.err.println("2. Login attemptAuthentication: Not Matched");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        return authentication; // Security session에 저장
    }

    /**
     * 로그인 완료시 호출되는 함수
     * Refresh token을 발행해 HttpServletResponse에 담는다.
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        // TbUserId로 리프레시토큰 발급
        String refreshToken = authService.createRefreshToken(principalDetails.getTbUser().getId());
        JwtTokenDto jwtTokenDto = authService.issueAccessToken(refreshToken);
        System.out.println("jwtTokenDto = " + jwtTokenDto.getAccessToken());

        // header에 담아서 전달
        response.addHeader(externalProperties.getRefreshKey(), externalProperties.getTokenPrefix() + refreshToken);
        response.addHeader(externalProperties.getAccessKey(), externalProperties.getTokenPrefix() + jwtTokenDto.getAccessToken());
        /*
            // 가끔 보안 문제로 header가 날라가는 경우가 있음.. 그때는 보안에 조금 취약하지만 바디에 담아서 보내준다.
            response.getWriter().write(externalProperties.getTokenPrefix() + refreshToken);
         */
        setSuccessResponse(response, "Login success");
        System.out.println("successfulAuthentication: login success");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        System.out.println("unsuccessfulAuthentication : failed");
        String failReason = failed.getMessage();

        setFailResponse(response, failReason);
    }

    private void setSuccessResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json; charset=UTF-8");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        jsonObject.put("code", 1);
        jsonObject.put("message", message);

        response.getWriter().print(jsonObject);
    }

    private void setFailResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("application/json; charset=UTF-8");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", false);
        jsonObject.put("code", -1);
        jsonObject.put("message", message);

        response.getWriter().print(jsonObject);
    }
}
