package com.mercadolibre.socialmeli.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Product {
    int product_id;
    String productName;
    String type;
    String brand;
    String color;
    String notes;
}
