package com.example.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration for WebClient to connect to external APIs.
 */
@Configuration
public class WebClientConfig {

    /**
     * WebClient bean for making HTTP requests to external APIs.
     */
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }
}
