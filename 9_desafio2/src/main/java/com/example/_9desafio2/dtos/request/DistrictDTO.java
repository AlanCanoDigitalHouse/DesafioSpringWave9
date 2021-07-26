package com.example._9desafio2.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictDTO {

    @NotBlank(message = "El barrio no puede estar vacío.")
    @Size(min=1,max = 45,message = "La longitud del nombre del barrio no puede superar los 45 caracteres.")
    private String district_name;

    @NotNull(message = "El precio de un barrio no puede estar vacío.")
    @Max(message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.",value = 4000)
    private Double district_price;

}
