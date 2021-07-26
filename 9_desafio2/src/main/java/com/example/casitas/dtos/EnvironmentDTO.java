package com.example.casitas.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@Validated
public class EnvironmentDTO {

    @JsonProperty("environment_name")
    @Size(max = 30,message = "La longitud del nombre no puede superar los 30 caracteres")
    @NotNull(message = "El nombre del ambiente no puede estar vacío")
    private String environmentName;

    @JsonProperty("environment_width")
    @NotNull(message = "El ancho del ambiente no puede estar vacío")
    @Max(value = 25, message = "El máximo largo permitido es de 25 mts")
    private Double environmentWidth;


    @JsonProperty("environment_length")
    @NotNull(message = "El largo de ambiente no puede estar vacío")
    @Max(value = 33, message = "El maximo largo permitido es de 33 mts.")
    private Double environmentLength;

}
