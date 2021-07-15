package com.mercadolibre.socialmeli.dto.request;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Validated
public class ProductDTO {
    private Integer product_id;
    @NotBlank(message = "The product must have a name")
    @NotEmpty(message = "The product must have a name")
    @NotNull(message = "The product must have a name")
    private String productName;
    @NotBlank(message = "Type can't be blank")
    @NotEmpty(message = "Type can't be blank")
    @NotNull(message = "The product must have a type")
    private String type;
    @NotBlank(message = "Brand can't be blank")
    @NotEmpty(message = "Brand can't be blank")
    @NotNull(message = "The product must have a brand")
    private String brand;
    @NotBlank(message = "Color can't be blank")
    @NotEmpty(message = "Color can't be blank")
    @NotNull(message = "The product must have a color")
    private String color;
    @NotBlank(message = "notes are mandatory")
    @NotEmpty(message = "notes are mandatory")
    @NotNull(message = "notes are mandatory")
    private String notes;
}
