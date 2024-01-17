package com.spring.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringBasicCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBasicCrudApplication.class, args);
    }

}
