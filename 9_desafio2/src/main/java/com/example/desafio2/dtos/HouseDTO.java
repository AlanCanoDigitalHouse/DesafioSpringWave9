package com.example.desafio2.dtos;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@Validated
@Data
public class HouseDTO {
    @NotNull(message = "El nombre de la propiedad no debe estar vacía")
    @Pattern(regexp = "([A-Z]).+",message = "EL nombre de la propiedad debe comenzar con una mayuscula")
    private String prop_name;
    @NotNull(message = "El nombre del barrio no puede estar  vacío")
    @Size(min=3,max = 45,message = "La longitud de un barrio deberia estar entre 3 y 45 caracteres" )
    private String  district_name;
    @NotNull(message = "El precio de un barrio no puede estar vacío")
    @Max(value = 4000,message = "El precio maximo permitido por metro cuadrado no puede superar los 4000 U$S")
    @Min(value = 1,message = "Un metro cuadrado no debe valer menos de 1 U$S")
    private double district_price;
    @NotEmpty(message = "La lista de ambientes debe tener al menos 1 ambiente")
    @NotNull(message = "La lista de ambientes no puede ser null")
    @Valid
    private List<EnvDTO> environments;
}
