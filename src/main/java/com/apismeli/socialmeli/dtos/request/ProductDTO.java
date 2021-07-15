package com.apismeli.socialmeli.dtos.request;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class ProductDTO {
    @NotNull
    private int product_id;

    @NotNull
    private String productName;

    @NotNull
    private String type;

    @NotNull
    private String brand;

    @NotNull
    private String color;

    @NotNull
    private String notes;
}
