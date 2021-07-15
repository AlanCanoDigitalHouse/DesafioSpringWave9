package com.jbianchini.meli.socialmeli.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;
}
