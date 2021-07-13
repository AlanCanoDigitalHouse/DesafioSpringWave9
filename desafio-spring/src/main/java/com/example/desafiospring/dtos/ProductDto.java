package com.example.desafiospring.dtos;

import lombok.Data;

@Data
public class ProductDto {

    private Long product_id;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;

}
