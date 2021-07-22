package com.bootcamp.desafio2.dtos.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class HabitacionDto {

    @NotNull(message = "El nombre de la habitacion es obligatorio")
    @NotBlank(message = "El nombre de la habitacion no puede ser vacio")
    private String nombre;

    @NotNull(message = "El ancho es obligatorio")
    @Min(message = "El ancho no puede ser menor a 0", value = 0)
    @Max(message = "El ancho no puede superar los 30 metros", value = 30)
    private Double ancho;

    @NotNull(message = "El largo es obligatorio")
    @Min(message = "El largo no puede ser menor a 0", value = 0)
    @Max(message = "El largo no puede superar los 30 metros", value = 30)
    private Double largo;

    private Double metrosCuadrados;

}
