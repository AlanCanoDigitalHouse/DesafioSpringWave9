package com.example.desafiospring.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {


    private Integer product_id;

    @NotNull
    @NotBlank(message = "Product name is mandatory")
    private String productName;

    @NotNull
    @NotBlank(message = "Type is mandatory")
    private String type;

    @NotNull
    @NotBlank(message = "Brand name is mandatory")
    private String brand;

    @NotNull
    @NotBlank(message = "Color name is mandatory")
    private String color;

    @NotNull
    @NotBlank(message = "notes name is mandatory")
    private String notes;


}
