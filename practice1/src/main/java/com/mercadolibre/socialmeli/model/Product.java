package com.mercadolibre.socialmeli.model;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class Product {
    private Integer product_id;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;
}
