package com.mercadolibre.tucasita.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyInfoResponseDTO {
    private Double totalSquareFeet;
    private Double totalPrice;
    private EnvironmentInfoResponseDTO biggerEnvironment;
    private List<EnvironmentInfoResponseDTO> environmentList;
}
