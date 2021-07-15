package com.example.DesafioSpring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Product {


    private Integer product_id;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;

}
