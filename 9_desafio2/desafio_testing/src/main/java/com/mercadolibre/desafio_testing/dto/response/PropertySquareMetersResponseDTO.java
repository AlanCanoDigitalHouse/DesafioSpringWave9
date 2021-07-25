package com.mercadolibre.desafio_testing.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class PropertySquareMetersResponseDTO {
    private String property_name;
    private double total_square_mts;
}
