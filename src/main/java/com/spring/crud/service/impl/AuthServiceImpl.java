package com.spring.crud.service.impl;

import com.amazonaws.services.cloudtrail.model.InvalidTokenException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.spring.crud.dto.RefreshTokenDto;
import com.spring.crud.dto.RefreshTokenDto.RefreshTokenCreateDto;
import com.spring.crud.repository.RefreshTokenRepository;
import com.spring.crud.security.ExternalProperties;
import com.spring.crud.security.JwtTokenDto;
import com.spring.crud.service.AuthService;
import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final ExternalProperties externalProperties;
    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public AuthServiceImpl(ExternalProperties externalProperties, RefreshTokenRepository refreshTokenRepository) {
        this.externalProperties = externalProperties;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public Algorithm getTokenAlgorithm() {
        return Algorithm.HMAC512(externalProperties.getTokenSecretKey());
    }

    /**
     * Access Token 생성을 위한 함수
     * Payload에 tbUser Id를 담는다.
     *
     * @param username
     */
    @Override
    public String createAccessToken(String username) {
        return JWT.create()
                .withSubject("accessToken")
                .withClaim("id", username)
                .withExpiresAt(new Date(System.currentTimeMillis() + externalProperties.getAccessTokenExpirationTime()))
                .sign(getTokenAlgorithm());
    }

    /**
     * Access Token 검증을 위한 함수
     * @param accessToken
     */
    @Override
    public String verifyAccessToken(String accessToken) throws JWTVerificationException {
        return JWT.require(getTokenAlgorithm())
                .build()
                .verify(accessToken)
                .getClaim("id").asString();
    }

    /**
     * Refresh Token 생성을 위한 함수
     * Payload에 tbuser id를 담는다.
     * DB(Redis)에 저장할 수도 있음.
     * Redis에 저장하는 경우, username를 key로, 발급한 token을 value로 저장
     * @param username
     * @return
     */
    @Override
    public String createRefreshToken(String username) {
        // refreshToken 이전 것들 모두 지우기 (이렇게 함으로써 다른 device에서 새롭게 로그인하는 경우, 기존의 로그인 되어 있던 device에서는 refresh 토큰을 사용할 수 없게 된다.)
        refreshTokenRepository.deleteAllByUsername(username);

        String refreshToken = JWT.create()
                .withSubject("refreshToken")
                .withClaim("id", username)
                .withExpiresAt(new Date(System.currentTimeMillis() + externalProperties.getRefreshTokenExpirationTime()))
                .sign(getTokenAlgorithm());
        // DB or Redis ...
        refreshTokenRepository.save(new RefreshTokenCreateDto(username, refreshToken).toEntity());
        return refreshToken;
    }

    /**
     * Refresh Token 검증을 위한 함수
     */
    @Override
    public String verifyRefreshToken(String refreshToken) throws JWTVerificationException {
        refreshTokenRepository.findByContent(refreshToken)
                .orElseThrow(() -> new InvalidTokenException("No such refresh token in DB"));

        return JWT.require(getTokenAlgorithm())
                .build()
                .verify(refreshToken)
                .getClaim("id").asString();
    }

    /**
     * Access Token 발급을 위한 함수
     * Refresh Token이 유효하다면 Access Token 발급.
     * @param refreshToken
     */
    @Override
    public JwtTokenDto issueAccessToken(String refreshToken) throws JWTVerificationException {
        String username = verifyRefreshToken(refreshToken); // 발행 전 검증
        String accessToken = createAccessToken(username); // 발행

        return JwtTokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

}
