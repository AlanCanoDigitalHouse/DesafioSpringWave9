package com.mercadolibre.calculadorametroscuadrados.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated

public class DistrictDTO {
    @NotBlank(message = "El barrio no puede estar vacio")
    @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres")
    private String district_name;

    @NotNull(message = "El precio de un barrio no puede estar vacio")
    @DecimalMax(message = "El precio maximo por metro cuadrado no puede superar los 4000", value = "4000.0")
    @DecimalMin(message = "El precio minimo por metro cuadrado no puede ser menor a 0", value = "0.0")
    private Double district_price;


}
