package com.desafio2.spring.tucasita.tucasita.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Data
@Validated
@AllArgsConstructor
public class RoomDTO {

    @NotBlank(message = "El nombre del ambiente no puede estar vacío.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    @Pattern(regexp = "^[A-Z][a-z ]+", message = "El nombre del ambiente debe comenzar con mayúscula.")
    private String environment_name;

    @NotNull(message = "El ancho del ambiente no puede estar vacío.")
    @Max(value = 25, message = "El ancho máximo permitido por ambiente es de 25 mts.")
    private Double environment_width;

    @NotNull(message = "El largo del ambiente no puede estar vacío.")
    @Max(value = 33, message = "El máximo largo permitido por propiedad es de 33 mts.")
    private Double environment_length;

}
