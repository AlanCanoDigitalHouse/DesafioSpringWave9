package com.mercadolibre.socialmeli.dto.request;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Validated
public class ProductDTO {
    private Integer product_id;
    @NotBlank(message = "The product must have a name")
    @NotEmpty(message = "The product must have a name")
    private String productName;
    @NotBlank(message = "Type can't be blank")
    @NotEmpty(message = "Type can't be blank")
    private String type;
    @NotBlank(message = "Brand can't be blank")
    @NotEmpty(message = "Brand can't be blank")
    private String brand;
    @NotBlank(message = "Color can't be blank")
    @NotEmpty(message = "Color can't be blank")
    private String color;
    @NotBlank(message = "notes are mandatory")
    @NotEmpty(message = "notes are mandatory")
    private String notes;
}
