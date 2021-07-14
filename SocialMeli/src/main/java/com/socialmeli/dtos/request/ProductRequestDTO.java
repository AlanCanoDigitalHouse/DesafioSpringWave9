package com.socialmeli.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class ProductRequestDTO {

    @NotNull(message = "Product id is null value")
    private Integer product_id;

    @NotNull(message = "Product name is null value")
    @NotBlank(message = "Product name is empty")
    private String productName;

    @NotNull(message = "Product type is null value")
    @NotBlank(message = "Product tyoe is empty")
    private String type;

    @NotNull(message = "Product brand is null value")
    @NotBlank(message = "Product brand is empty")
    private String brand;

    @NotNull(message = "Product color is null value")
    @NotBlank(message = "Product brand is empty")
    private String color;

    @NotNull(message = "Aditional notes is null value")
    private String notes;
}
