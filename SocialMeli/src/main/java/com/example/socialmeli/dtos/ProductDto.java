package com.example.socialmeli.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Integer product_id;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;
}
