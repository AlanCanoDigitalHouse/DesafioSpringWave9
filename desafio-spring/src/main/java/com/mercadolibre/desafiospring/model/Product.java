package com.mercadolibre.desafiospring.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
public class Product {
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;
}
