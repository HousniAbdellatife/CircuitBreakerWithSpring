package com.spring.training.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Item {
    private String name;
    private double price;
}
