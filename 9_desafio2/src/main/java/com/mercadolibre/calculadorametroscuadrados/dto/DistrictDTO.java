package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DistrictDTO {

    @NotNull(message = "El barrio no puede estar vacío.")
    @Length(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
    private String district_name;

    @NotNull(message = "El precio de un barrio no puede estar vacío.")
    @DecimalMax( value = "4000",message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.")
    private Double district_price;
}
