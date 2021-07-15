package com.mercadolibre.social_meli.entity;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Integer product_id;

    @NotNull(message = "A product name is needed")
    @NotBlank(message = "A product name is needed")
    private String productName;

    @NotNull(message = "A product type is needed")
    @NotBlank(message = "A product type is needed")
    private String type;

    @NotNull(message = "A product brand is needed")
    @NotBlank(message = "A product brand is needed")
    private String brand;

    @NotNull(message = "A product brand is needed")
    @NotBlank(message = "A product brand is needed")
    private String color;

    @NotNull(message = "A product brand is needed")
    @NotBlank(message = "A product brand is needed")
    private String notes;

}
