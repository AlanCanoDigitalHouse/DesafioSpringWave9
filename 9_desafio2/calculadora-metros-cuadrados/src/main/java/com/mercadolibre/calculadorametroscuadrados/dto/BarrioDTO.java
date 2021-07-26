package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@Validated
@NoArgsConstructor
public class BarrioDTO {

    @NotBlank (message = "El barrio no puede estar vacío")
    @Size(max =45,message = "La longitud del barrio no puede superar los 45 caracteres.")
    private String district_name;

    @NotNull(message = "El precio de un barrio no puede estar vacío")
    @Max(value = 4000,message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.")
    private Integer district_price;

}
