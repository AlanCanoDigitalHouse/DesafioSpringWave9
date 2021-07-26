package com.example._9desafio2.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class EnviromentDTO {

    @NotBlank(message = "El nombre del ambiente no puede estar vacío.")
    @Pattern(regexp = "([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$",message = "El nombre del ambiente debe comenzar con mayúscula.")
    @Size(min = 1,max = 30,message ="La longitud del nombre no puede superar los 30 caracteres.")
    private String environment_name;

    @NotNull(message = "El ancho del ambiente no puede estar vacío.")
    @Max(message = "El máximo ancho permitido por propiedad es de 25 mts.",value = 25)
    private Double environment_width;

    @NotNull(message = "El largo del ambiente no puede estar vacío")
    @Max(message = "El máximo largo permitido por propiedad es de 33 mts.",value = 33)
    private Double environment_length;


    public Double getSquareFeet() {
        Double result = 0.0;
        if(this.environment_width != null && this.environment_length != null)
            result = this.environment_width * this.environment_length;
        return result;
    }
}
