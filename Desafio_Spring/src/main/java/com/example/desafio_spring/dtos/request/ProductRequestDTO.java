package com.example.desafio_spring.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

@Data
public class ProductRequestDTO {
    private Integer product_id;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;

    public ProductRequestDTO(Integer product_id, String productName, String type, String brand, String color, String notes) {
        this.product_id = product_id;
        this.productName = productName;
        this.type = type;
        this.brand = brand;
        this.color = color;
        this.notes = notes;
    }
}
