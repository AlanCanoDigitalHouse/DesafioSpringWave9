package com.jbianchini.meli.socialmeli.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class ProductDTO {

    @NotNull(message = "Please enter a product name")
    @NotBlank(message = "Product name cannot be blank")
    @Size(min = 1, max = 200, message = "Product name must be between 1 and 100 characters")
    private String productName;

    @NotNull(message = "Please enter a product type")
    @NotBlank(message = "Product type cannot be blank")
    @Size(min = 1, max = 20, message = "Product type must be between 1 and 20 characters")
    private String type;

    @Size(min = 1, max = 20, message = "Product brand must be between 1 and 20 characters")
    private String brand;

    @Size(min = 1, max = 20, message = "Product color must be between 1 and 20 characters")
    private String color;

    @NotNull(message = "Please enter a product description")
    @NotBlank(message = "Product description cannot be blank")
    @Size(min = 1, max = 200, message = "Product notes must be between 1 and 200 characters")
    private String notes;
}
