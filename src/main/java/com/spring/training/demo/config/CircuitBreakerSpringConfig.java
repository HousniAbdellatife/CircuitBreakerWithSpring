package com.spring.training.demo.config;


import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CircuitBreakerSpringConfig {

    @Bean
    public CircuitBreakerConfig circuitBreakerSpringConfig() {
        return CircuitBreakerConfig.ofDefaults();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
