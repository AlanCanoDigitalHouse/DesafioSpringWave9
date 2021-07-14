package com.mercadolibre.socialmeli.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Validated
public class ProductDTO {
    private Integer product_id;
    @NotBlank
    @NotEmpty
    private String productName;
    @NotBlank
    @NotEmpty
    private String type;
    @NotBlank
    @NotEmpty
    private String brand;
    @NotBlank
    @NotEmpty
    private String color;
    @NotBlank
    @NotEmpty
    private String notes;
}
