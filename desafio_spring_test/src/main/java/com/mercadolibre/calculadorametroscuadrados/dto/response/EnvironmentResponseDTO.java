package com.mercadolibre.calculadorametroscuadrados.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnvironmentResponseDTO {
    private String environment_name;
    private Double environment_area;
}
