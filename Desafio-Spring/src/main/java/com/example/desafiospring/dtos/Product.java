package com.example.desafiospring.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {


    private Integer product_id;

    @NotBlank(message = "Product name is mandatory")
    private String productName;

    @NotBlank(message = "Type is mandatory")
    private String type;

    @NotBlank(message = "Brand name is mandatory")
    private String brand;

    @NotBlank(message = "Color name is mandatory")
    private String color;

    @NotBlank(message = "notes name is mandatory")
    private String notes;


}
