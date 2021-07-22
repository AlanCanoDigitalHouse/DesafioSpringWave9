package com.mercadolibre.desafiotesting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;

@Data
@AllArgsConstructor
@Validated
public class RequestHouseDto {

    @Valid
    private HouseDto houseDto;
    @Max(value = 4000,message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.")
    private Double district_price;
}
