package com.example.tucasitatasaciones.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class DistrictDto {

    @NotBlank(message = "El barrio no puede estar vacío")
    @Size(max = 54, message = "La longitud del barrio no puede superar los 45 caracteres.")
    private String name;

    @NotNull(message = "El precio de un barrio no puede estar vacío")
    @DecimalMax(value = "4000.0", message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S")
    private Double price;
}
