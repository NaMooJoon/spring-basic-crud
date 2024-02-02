package com.spring.crud.service;


import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.spring.crud.security.JwtTokenDto;

public interface AuthService {

    Algorithm getTokenAlgorithm();

    String createAccessToken(String tbUserId);

    String verifyAccessToken(String accessToken) throws JWTVerificationException;

    String createRefreshToken(String tbUserId);

    String verifyRefreshToken(String refreshToken) throws JWTVerificationException;

    JwtTokenDto issueAccessToken(String refreshToken) throws JWTVerificationException;
}
