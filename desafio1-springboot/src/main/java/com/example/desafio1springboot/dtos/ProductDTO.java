package com.example.desafio1springboot.dtos;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
public class ProductDTO {

    @Min(value = 1, message = "El atributo no puede ser menor a 1")
    @NotNull(message = "El atributo no puede ser nulo")
    private Integer product_id;

    @NotNull(message = "El atributo no puede ser nulo")
    @NotBlank(message = "El atributo no puede estar vacio")
    private String productName;

    @NotNull(message = "El atributo no puede ser nulo")
    @NotBlank(message = "El atributo no puede estar vacio")
    private String type;

    @NotNull(message = "El atributo no puede ser nulo")
    @NotBlank(message = "El atributo no puede estar vacio")
    private String brand;

    @NotNull(message = "El atributo no puede ser nulo")
    @NotBlank(message = "El atributo no puede estar vacio")
    private String color;

    @NotNull(message = "El atributo no puede ser nulo")
    @NotBlank(message = "El atributo no puede estar vacio")
    private String notes;
}
