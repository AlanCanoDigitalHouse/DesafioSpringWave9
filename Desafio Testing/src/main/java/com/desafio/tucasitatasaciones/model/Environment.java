package com.desafio.tucasitatasaciones.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Environment {
    @NotBlank(message = "El nombre del ambiente no puede estar vacío.")
    @Pattern(regexp = "(([A-Z][a-z-áéíóúñ]+)+ ?)+", message = "El nombre del ambiente debe comenzar con mayúscula.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    private String environment_name;

    @Positive(message = "El ancho del ambiente no puede estar vacío.")
    @Max(value = 25, message = "El máximo ancho permitido por propiedad es de 25 mts.")
    private double environment_width;

    @Positive(message = "El largo del ambiente no puede estar vacío.")
    @Max(value = 33, message = "El máximo largo permitido por propiedad es de 33 mts.")
    private double environment_length;
    private double environment_area;

    public Environment(String environment_name, double environment_width, double environment_length){
        this.environment_name = environment_name;
        this.environment_width = environment_width;
        this.environment_length = environment_length;
        this.environment_area = environment_length*environment_width;
    }
}
