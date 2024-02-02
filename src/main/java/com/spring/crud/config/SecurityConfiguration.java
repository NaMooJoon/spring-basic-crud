package com.spring.crud.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.crud.repository.TbUserRepository;
import com.spring.crud.security.ExternalProperties;
import com.spring.crud.security.FilterExceptionHandlerFilter;
import com.spring.crud.security.JwtAuthenticationFilter;
import com.spring.crud.security.JwtAuthorizationFilter;
import com.spring.crud.service.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfiguration {

    private final TbUserRepository tbUserRepository;
    private final CorsFilterConfiguration corsFilterConfiguration;
    private final ObjectMapper objectMapper;
    private final AuthService authService;
    private final ExternalProperties externalProperties;

    public SecurityConfiguration(TbUserRepository tbUserRepository, CorsFilterConfiguration corsFilterConfiguration,
                                 ObjectMapper objectMapper, AuthService authService, ExternalProperties externalProperties) {
        this.tbUserRepository = tbUserRepository;
        this.corsFilterConfiguration = corsFilterConfiguration;
        this.objectMapper = objectMapper;
        this.authService = authService;
        this.externalProperties = externalProperties;
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Spring security 권한 설정
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // session을 안쓰는 경우(STATELESS)
                .formLogin(formLogin -> formLogin.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .apply(new CustomDsl()); // TODO: deprecated

        return httpSecurity.build();
    }

    public class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {

        /**
         *  Jwt Token Authentication을 위한 filter 설정.
         *
         *  jwtAuthenticationFilter: 인증을 위한 필터("/api/login")
         *  JwtAuthorizationFilter: 인가를 위한 필터
         *  FilterExceptionHandlerFilter: TokenExpiredException 핸들링을 위한 필터
         */
        @Override
        public void configure(HttpSecurity http) throws Exception {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, objectMapper, authService, externalProperties);
            jwtAuthenticationFilter.setFilterProcessesUrl("/api/login");

            http.addFilter(corsFilterConfiguration.corsFilter())
                    .addFilter(jwtAuthenticationFilter)
                    .addFilter(new JwtAuthorizationFilter(authenticationManager, tbUserRepository, authService, externalProperties))
                    .addFilterBefore(new FilterExceptionHandlerFilter(), BasicAuthenticationFilter.class);
        }

    }

}
