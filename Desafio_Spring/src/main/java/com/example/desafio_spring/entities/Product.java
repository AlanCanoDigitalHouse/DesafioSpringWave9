package com.example.desafio_spring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private Integer product_id;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;
}
