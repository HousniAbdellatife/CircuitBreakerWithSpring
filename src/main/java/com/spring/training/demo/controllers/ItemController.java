package com.spring.training.demo.controllers;

import com.spring.training.demo.models.Item;
import feign.Response;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class ItemController {

    private AtomicInteger counter = new AtomicInteger(0);
    private Item item = new Item("item1", 99);
    private static final int TRESHOLD = 5;

    @GetMapping("/item")
    public Item getItem() {
        if (counter.incrementAndGet() < TRESHOLD) return item;
        throw new IllegalArgumentException("Item controller start de throw an exception");
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(exception.getMessage());
    }
}
