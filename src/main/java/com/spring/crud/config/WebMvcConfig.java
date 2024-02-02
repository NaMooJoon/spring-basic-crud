package com.spring.crud.config;

import com.spring.crud.interceptor.DefaultInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * file 접근 통로 열어두고 싶다면,,,
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry
                .addResourceHandler("/files/**") // 접근할 파일이 있는 위치를 지정 (url)
                .addResourceLocations("file:" + "/Users/joonk/study/handong/spring-basic-crud/Users/workspace/uploadfiles/")
                .setCachePeriod(60 * 5)  // 60 sec * 5 = 5 min, 브라우저에 캐싱하는 시간
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DefaultInterceptor())
                .addPathPatterns("/api/**") // interceptor 가 실행되는 url 패턴
                .excludePathPatterns("/resources/**");
    }
}
