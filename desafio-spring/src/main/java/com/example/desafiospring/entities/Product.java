package com.example.desafiospring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long id_post;
    private Long product_id;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;

}
