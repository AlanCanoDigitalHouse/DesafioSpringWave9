package com.bootcamp.desafio2.dtos.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CasaDto {

    @NotNull(message = "El nombre es obligatorio")
    @NotBlank(message = "El nombre no puede ser vacio")
    private String nombre;

    @NotNull
    @NotBlank
    private String direccion;

    @NotEmpty(message = "La casa debe tener almenos una habitacion")
    @Valid
    private List<HabitacionDto> habitaciones;


}
