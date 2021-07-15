package com.example.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {

    private int productId;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;

    public ProductModel(String productName, String type, String brand, String color, String notes) {
    }
}
