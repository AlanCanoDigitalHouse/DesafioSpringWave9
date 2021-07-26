package com.metroscuadrados.metroscuadrados.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitacionDTO {

    @Pattern(message = "El nombre debe empezar con mayuscula", regexp = "^[A-Z][a-z]+$")
    @Size(min =1,max= 30 , message = "El nombre puede tener maximo 30 caracteres")
    private String nombre;

    @NotEmpty
    @Max(25)
    private double ancho;

    @NotEmpty
    @Max (33)
    private double largo;
}
