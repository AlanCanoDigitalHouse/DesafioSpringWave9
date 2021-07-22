package com.example.desafio2.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Validated
@Data
@AllArgsConstructor
public class EnvDTO {
    @NotNull(message = "El nombre del ambiente no puede estar vacío")
    @Pattern(regexp = "([A-Z]).+",message = "El nombre del ambiente debe comenzar en mayuscula")
    @Size(max = 30,message = "La longitud del nombre no puede superar los 30 caracteres")
    private String environment_name;
    @NotNull(message = "El ancho del ambiente no puede estar vacío")
    @Max(value = 25,message = "El máximo largo permitido es de 25 mts")
    private double environment_width;
    @NotNull(message = "El largo de ambiente no puede estar vacío")
    @Max(value = 33,message = "El maximo largo permitido es de 33 mts.")
    private double environment_length;
}
