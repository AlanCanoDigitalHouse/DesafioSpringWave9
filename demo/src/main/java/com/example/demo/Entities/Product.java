package com.example.demo.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private String productName;
    private int productId;
    private String productColor;
    private String productBrand;
}
