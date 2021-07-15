package com.meli.joescaos.socialmeli.socialmeli.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;
}
