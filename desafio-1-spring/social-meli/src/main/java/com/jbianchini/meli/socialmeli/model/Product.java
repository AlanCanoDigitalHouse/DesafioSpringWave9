package com.jbianchini.meli.socialmeli.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {
    //TODO: Remove this attribute
    private Integer productId;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;
    private Post post;
}
