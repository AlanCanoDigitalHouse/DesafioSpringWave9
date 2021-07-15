package com.mercado_libre.bootcamp.spring.desafio.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @NotBlank(message = "El campo productName no debe ser nulo ni vacio")
    private String productName;

    @NotBlank(message = "El campo type no debe ser nulo ni vacio")
    private String type;

    @NotBlank(message = "El campo brand no debe ser nulo ni vacio")
    private String brand;

    @NotBlank(message = "El campo color no debe ser nulo ni vacio")
    private String color;

    @NotBlank(message = "El campo notes no debe ser nulo ni vacio")
    private String notes;
}
