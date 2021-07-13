package com.meli.desafiospring.models;

import lombok.Data;

@Data
public class Product {

    Integer product_id;
    String productName;
    String type;
    String brand;
    String color;
    String notes;
}
