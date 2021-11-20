package com.spring.training.demo.services;

import com.spring.training.demo.apiClient.ItemApiClient;
import com.spring.training.demo.models.Item;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.autoconfigure.CircuitBreakerProperties;
import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigurationProperties;
import io.github.resilience4j.fallback.FallbackDecorators;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreaker;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.function.Supplier;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemApiClient itemApiClient;

    @Autowired
    private CircuitBreakerProperties properties;

    private CircuitBreaker orderServiceCircuitBreaker;

    @PostConstruct
    public void init() {
        CircuitBreakerConfigurationProperties.InstanceProperties instanceProperties = properties.getBackendProperties("orderService");

        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .automaticTransitionFromOpenToHalfOpenEnabled(instanceProperties.getAutomaticTransitionFromOpenToHalfOpenEnabled())
                .failureRateThreshold(instanceProperties.getFailureRateThreshold())
                .minimumNumberOfCalls(instanceProperties.getMinimumNumberOfCalls())
                .permittedNumberOfCallsInHalfOpenState(instanceProperties.getPermittedNumberOfCallsInHalfOpenState())
                .slidingWindowSize(instanceProperties.getSlidingWindowSize())
                .waitDurationInOpenState(instanceProperties.getWaitDurationInOpenState())
                .slidingWindowType(instanceProperties.getSlidingWindowType())
                .build();

        orderServiceCircuitBreaker = CircuitBreaker.of("orderService1", config);
    }

    @Override
    public Item getItem() {
        Supplier<Item> itemSupplier = () -> itemApiClient.getItem();
        Supplier<Item> decoratedItemSupplier = orderServiceCircuitBreaker.decorateSupplier(itemSupplier);
        return decoratedItemSupplier.get();
    }

    @Override
    @Bulkhead(name = "")
    @io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker(name = "orderService", fallbackMethod = "orderFallbackMethod")
    public Item getItemWithAnnotatedCircuitBreakerMethod() {
       return itemApiClient.getItem();
    }

    public Item orderFallbackMethod(Exception exception) {
        return new Item("defautl Item when circuit breaker is opened", 0);
    }
}
