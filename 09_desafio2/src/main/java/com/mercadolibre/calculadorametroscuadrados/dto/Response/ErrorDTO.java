package com.mercadolibre.calculadorametroscuadrados.dto.Response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDTO {
    private String name;
    private String description;
}
