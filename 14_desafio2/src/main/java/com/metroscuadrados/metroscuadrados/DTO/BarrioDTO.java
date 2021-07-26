package com.metroscuadrados.metroscuadrados.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
public class BarrioDTO {

    @Size(min =1,max= 45 , message = "El nombre del barrio puede maximo 45 caracteres")
    @NotEmpty(message = "Debe ingresar el nombre del barrio")
    String nombre;
    Integer valor;

}
