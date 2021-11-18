package com.spring.training.demo.config;


import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CircuitBreakerSpringConfig {

    public CircuitBreakerConfig circuitBreakerSpringConfig() {
        return CircuitBreakerConfig.ofDefaults();
    }
}
