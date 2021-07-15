package com.desafio.spring.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;


}
