package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.*;


@Builder
@Data
@AllArgsConstructor
public class EnvironmentResponseDTO {
    private final String environment_name;
    private final Double environment_width;
    private final Double environment_length;
    private final Double squareFeet;

}
