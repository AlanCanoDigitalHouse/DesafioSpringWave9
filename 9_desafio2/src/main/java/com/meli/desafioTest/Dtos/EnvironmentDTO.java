package com.meli.desafioTest.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class EnvironmentDTO {
    @NotNull(message = "El nombre del ambiente no estar vacío.")
    @NotEmpty(message = "El nombre del ambiente no estar vacío.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    private String environment_name;
    @NotNull(message = "El ancho del ambiente no puede estar vacío.")
    @DecimalMax(value = "25", message = "El máximo ancho permitido por propiedad es de 25 mts.")
    private Double environment_width;
    @NotNull(message = "El largo del ambiente no puede estar vacío.")
    @DecimalMax(value = "33.0", message = "El máximo largo permitido por propiedad es de 33 mts.")
    private Double environment_length;
    private Double environment_squareMeters;

    public EnvironmentDTO(String environment_name, Double environment_width, Double environment_length) {
        this.environment_name = environment_name;
        this.environment_width = environment_width;
        this.environment_length = environment_length;
        this.environment_squareMeters = getSquareMeters();
    }

    //Calcula la cantidad de metros cuadrados de la habitacion actual
    //Se usa en el cosntructor para asignar esta medida al momento de instanciar la entidad
    public Double getSquareMeters() {
        Double result = 0.0;
        if (this.environment_width != null && this.environment_length != null)
            result = this.environment_width * this.environment_length;
        return result;
    }
}
