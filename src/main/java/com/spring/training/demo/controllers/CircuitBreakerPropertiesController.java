package com.spring.training.demo.controllers;

import io.github.resilience4j.circuitbreaker.autoconfigure.CircuitBreakerProperties;
import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("/circuitbreaker")
public class CircuitBreakerPropertiesController {

    @Autowired
    private CircuitBreakerProperties properties;

    @GetMapping("/instances")
    public Map<String, CircuitBreakerConfigurationProperties.InstanceProperties> getConfigInstances() {
        return properties.getInstances(); // == properies.getBackends();
    }

    @GetMapping("/instances/{backend}")
    public CircuitBreakerConfigurationProperties.InstanceProperties getConfigInstance(@PathVariable String backend) {
        return properties.getBackendProperties(backend); // == properies.getBackends();
    }


}
