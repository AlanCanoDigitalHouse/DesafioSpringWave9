package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.*;


@Builder
@Data
@AllArgsConstructor
public class EnvironmentResponseDTO {
    private String environment_name;
    private Double environment_width;
    private Double environment_length;
    private Double squareFeet;
}
