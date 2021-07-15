package com.mercadolibre.socialmeli.dtos.posts.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRequestDTO {

    @NotNull(message = "productName is mandatory")
    @NotBlank(message = "productName can't be white spaces")
    @NotEmpty(message = "productName is mandatory")
    private String productName;

    @NotNull(message = "type is mandatory")
    @NotBlank(message = "type can't be white spaces")
    @NotEmpty(message = "type is mandatory")
    private String type;

    @NotNull(message = "brand is mandatory")
    @NotBlank(message = "brand can't be white spaces")
    @NotEmpty(message = "brand is mandatory")
    private String brand;

    @NotNull(message = "color is mandatory")
    @NotBlank(message = "color can't be white spaces")
    @NotEmpty(message = "color is mandatory")
    private String color;

    @NotNull(message = "notes is mandatory")
    @NotBlank(message = "notes can't be white spaces")
    @NotEmpty(message = "notes is mandatory")
    private String notes;

}
