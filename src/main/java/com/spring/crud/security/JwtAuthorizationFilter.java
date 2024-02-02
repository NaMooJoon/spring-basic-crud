package com.spring.crud.security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.spring.crud.domain.TbUser;
import com.spring.crud.exception.NoMatchedDataException;
import com.spring.crud.repository.TbUserRepository;
import com.spring.crud.service.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Login시 JWT 방식을 사용하면, Authentication Filter에서는 요청헤더를 인코딩하여 토큰으로 변환시키고, authenticate()를 통해 토큰과 DB의 사용자 정보를 비교하여 검증한다. 그
 * 이후 토큰을 만들어 응답헤더에 설정한다..
 * <p>
 * BasicAuthenticationFilter: 스프링 시큐리티에서 기본적으로 제공하는 필터 중 하나, HTTP의 요청 헤더를 이용한 BASIC한 방식으로 유저 인증을 처리. 생성된 result를
 * SecurityContextHolder에 넣어 반환.
 */

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbUserRepository tbUserRepository;
    private final AuthService authService;
    private final ExternalProperties externalProperties;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, TbUserRepository tbUserRepository,
                                  AuthService authService, ExternalProperties externalProperties) {
        super(authenticationManager);
        this.tbUserRepository = tbUserRepository;
        this.authService = authService;
        this.externalProperties = externalProperties;
    }

    /**
     * 권한 인가를 위한 함수 Access Token을 검증하고 유효하면 Authentication을 직접 생성해 SecurityContextHolder에 넣는다.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String jwtHeader = request.getHeader(externalProperties.getAccessKey());
        if (jwtHeader == null || !jwtHeader.startsWith(externalProperties.getTokenPrefix())) {
            // 토큰이 없을 시 Authentication이 없는 상태로 doFilter -> Spring Security가 잡아냄;
            chain.doFilter(request, response);
            return;
        }
        try {
            String accessToken = jwtHeader.replace(externalProperties.getTokenPrefix(), "");
            String tbUserId = authService.verifyAccessToken(accessToken);

            // 1. 유저 조회, 없을 시 return NoMatchingDataException(404)
            TbUser tbUserEntity = tbUserRepository.findEntityGraphRoleTypeById(tbUserId)
                    .orElseThrow(() -> new NoMatchedDataException("AccessToken에 해당되는 user가 없음."));

            // 2. Authentication 생성
            PrincipalDetails principalDetails = new PrincipalDetails(tbUserEntity);
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

            // 3. SecurityContextHolder에 Authentication을 담는다 : Spring Security가 권한 처리 할 수 있도록 한다.
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (TokenExpiredException | JWTDecodeException | NoMatchedDataException e) {
            logger.error("ACCESS IS EXPIRED!!");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        chain.doFilter(request, response);
    }

    /**
     * refreshToken, accessToken이 request Header에 포함되어 있는 지 확인.
     * TODO: refreshToken의 경우, accessToken이 만료되는 경우 프론트 측에서 다시 refreshToken 포함해서 보내도록.
     */
    private boolean isRequestJwt(HttpServletRequest request) {
        String accessToken = request.getHeader(externalProperties.getAccessKey());

        if (accessToken == null || !accessToken.startsWith(externalProperties.getTokenPrefix())) {
            return false;
        }
        return true;
    }

}
