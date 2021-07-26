package com.mercadolibre.calculadorametroscuadrados.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EnvironmentDTO {
    @NotBlank(message = "El nombre del ambiente no puede estar vacio")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres")
    @Pattern(regexp = "^[A-Z].*", message = "El nombre del ambiente debe comenzar con mayuscula")
    private String environment_name;
    @NotNull(message = "El ancho del ambiente no puede estar vacio")
    @DecimalMax(message = "El maximo ancho permitido por propiedad es de 25 mts", value = "25.0")
    @DecimalMin(message = "El minimo ancho permitido por propiedad es de 0 mts", value = "0.0")
    private Double environment_width;
    @NotNull(message = "El largo del ambiente no puede estar vacio")
    @DecimalMax(message = "El maximo largo permitido por propiedad es de 33 mts", value = "33.0")
    @DecimalMin(message = "El minimo largo permitido por propiedad es de 0 mts", value = "0.0")
    private Double environment_length;
    private Double environment_square_meters;


    public EnvironmentDTO(@NotBlank(message = "El nombre del ambiente no puede estar vacio") @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres") @Pattern(regexp = "[A-Z-ZÀ-Ö][a-zA-ZÀ-ÖØ-öø-ÿ]*", message = "El nombre del ambiente debe comenzar con mayuscula") String environment_name, @NotNull(message = "El ancho del ambiente no puede estar vacio") @DecimalMax(message = "El maximo ancho permitido por propiedad es de 25 mts", value = "25.0") @DecimalMin(message = "El minimo ancho permitido por propiedad es de 0 mts", value = "0.0") Double environment_width, @NotNull(message = "El largo del ambiente no puede estar vacio") @DecimalMax(message = "El maximo largo permitido por propiedad es de 33 mts", value = "33.0") @DecimalMin(message = "El minimo largo permitido por propiedad es de 0 mts", value = "0.0") Double environment_length) {
        this.environment_name = environment_name;
        this.environment_width = environment_width;
        this.environment_length = environment_length;
    }
}
