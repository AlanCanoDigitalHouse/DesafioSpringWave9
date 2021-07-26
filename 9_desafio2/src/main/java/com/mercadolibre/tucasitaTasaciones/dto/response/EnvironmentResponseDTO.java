package com.mercadolibre.tucasitaTasaciones.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EnvironmentResponseDTO {

    private String environment_name;
    private Double squareFeet;
}
