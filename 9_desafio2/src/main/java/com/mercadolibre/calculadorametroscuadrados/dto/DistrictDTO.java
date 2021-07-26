package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@Validated
public class DistrictDTO {

    @NotBlank(message = "El barrio no puede estar vacío.")
    @Size(max=45, message = "La longitud del barrio no puede superar los 45 caracteres.")
    private String district_name;

    @NotNull(message = "El precio de un barrio no puede estar vacío")
    @DecimalMax(value = "4000.0", message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.")
    private Double district_price;

}
