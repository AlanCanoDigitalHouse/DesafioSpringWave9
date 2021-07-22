package com.api.firstspringchallenge.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
public class Product {
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;
}
