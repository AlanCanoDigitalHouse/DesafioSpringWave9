package com.mercadolibre.desafio1.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Valid
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductRequestDTO {

    @NotEmpty(message = "El campo 'productName' no puede estas vacio.")
    @Size(min = 2, max = 50, message = "El campo 'productName' debe tener entre 2 y 50 caracteres.")
    private String productName;

    @NotEmpty(message = "El campo 'type' no puede estas vacio.")
    @Size(min = 1, max = 10, message = "El campo 'type' debe tener entre 1 y 10 caracteres.")
    private String type;

    @NotEmpty(message = "El campo 'brand' no puede estas vacio.")
    @Size(min = 1, max = 10, message = "El campo 'brand' debe tener entre 1 y 10 caracteres.")
    private String brand;

    @NotEmpty(message = "El campo 'color' no puede estas vacio.")
    @Size(min = 1, max = 10, message = "El 'color' debe tener entre 1 y 10 caracteres.")
    private String color;

    @NotEmpty(message = "El campo 'notes' no puede estas vacio.")
    @Size(min = 1, max = 100, message = "El campo 'notes' 'debe tener entre 1 y 100 caracteres.")
    private String notes;
}
