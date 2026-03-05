package dk.jnie.example.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main Spring Boot application.
 * This module ONLY wires beans and connects everything together.
 * It imports configurations from inbound and outbound modules.
 */
@SpringBootApplication
@EntityScan(basePackages = "dk.jnie.example.repository.entity")
@EnableJpaRepositories(basePackages = "dk.jnie.example.repository.repository")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
