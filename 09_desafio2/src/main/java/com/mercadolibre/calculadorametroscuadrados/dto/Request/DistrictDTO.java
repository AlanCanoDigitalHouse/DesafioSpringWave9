package com.mercadolibre.calculadorametroscuadrados.dto.Request;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class DistrictDTO {

    @NotBlank(message = "El barrio no puede estar vacío.")
    @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
    private String name;

    @NotNull(message = "El precio del barrio no puede estar vacío.")
    @DecimalMax(value = "4000", message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.")
    private Double price;
}
