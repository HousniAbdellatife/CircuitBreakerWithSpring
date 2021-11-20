package com.spring.training.demo.services;

import com.spring.training.demo.models.Item;

public interface ItemService {
    Item getItem();
    Item getItemWithAnnotatedCircuitBreakerMethod();
}
