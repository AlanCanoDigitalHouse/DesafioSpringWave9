package com.mercadolibre.tucasita.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnvironmentInfoResponseDTO {
    private String environment_name;
    private Double totalSquareFeet;
}
