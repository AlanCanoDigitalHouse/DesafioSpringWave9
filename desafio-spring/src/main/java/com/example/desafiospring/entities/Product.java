package com.example.desafiospring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {

    private Long id_post;
    private Long product_id;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;

}
