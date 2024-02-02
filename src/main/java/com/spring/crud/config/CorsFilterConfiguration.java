package com.spring.crud.config;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsFilterConfiguration {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true); // JSON 서버 응답을 자바스크립트에서 처리할 수 잇게 해줌
        config.setAllowedOriginPatterns(Arrays.asList("*")); // 모든 ip 응답을 허용
        config.addAllowedHeader("*"); // 모든 HTTP METHOD 허용
        config.addAllowedMethod("*"); // 모든 HTTP HEADER 허용

        String[] arrays = {"Authorization", "RefreshToken"};
        config.setAllowedHeaders(Arrays.asList(arrays));
        source.registerCorsConfiguration("/api/**", config);
        return new CorsFilter(source);
    }
}
