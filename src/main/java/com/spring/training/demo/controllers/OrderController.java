package com.spring.training.demo.controllers;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/order")
    @CircuitBreaker(name = "order service", fallbackMethod = "orderFallbackMethod")
    public ResponseEntity<String> createOrder() {
        String response = restTemplate.getForObject("http://localhost:8081/item", String.class);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<String> orderFallbackMethod() {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body("item service is down");
    }
}
