package com.example.socialmeli2.Modelos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Producto {

    private Integer product_id;
    private String productName;
    private String brand;
    private String  color;
    private String notes;
}
