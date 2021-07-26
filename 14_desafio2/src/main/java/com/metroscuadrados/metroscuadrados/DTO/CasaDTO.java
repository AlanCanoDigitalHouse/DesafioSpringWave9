package com.metroscuadrados.metroscuadrados.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CasaDTO {

    @Pattern(message = "El nombre debe empezar con mayuscula", regexp = "^[A-Z][a-z]+$")
    @Size(min =1,max= 30 , message = "El nombre puede tener maximo 30 caracteres")
    @NotEmpty (message = "Debe ingresar un nombre")
    private String nombre;

    private String direccion;

    private String barrio;

    private List<HabitacionDTO> habitaciones;

}
