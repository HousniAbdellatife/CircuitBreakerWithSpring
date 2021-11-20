package com.spring.training.demo.apiClient;


import com.spring.training.demo.models.Item;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "itemClient", url = "http://localhost:8080/item")
public interface ItemApiClient {

    @GetMapping
    Item getItem();
}
