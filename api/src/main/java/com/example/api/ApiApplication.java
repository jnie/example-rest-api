package com.example.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main Spring Boot application entry point.
 * This is the REST API module that exposes HTTP endpoints.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.example.api", "com.example.service", "com.example.persistence", "com.example.integration"})
@EntityScan(basePackages = "com.example.persistence.entity")
@EnableJpaRepositories(basePackages = "com.example.persistence.repository")
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}
