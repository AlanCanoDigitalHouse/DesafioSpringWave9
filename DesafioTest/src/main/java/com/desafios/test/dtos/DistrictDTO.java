package com.desafios.test.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictDTO {
    @NotEmpty(message = "El barrio no puede estar vacío.")
    @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracters.")
    private String district_name;

    @NotNull(message = "El precio del metro cuadrado del distrito no puede esta vacío.")
    @DecimalMin(value = "1", message = "El precio mínimo permitido por metro cuadrado no puede ser inferior a U$S 0.01")
    @DecimalMax(value = "4000", message = "El precio máximo permitido por metro cuadrado nno puede superer los U$S 4000.")
    private Double district_price;
}