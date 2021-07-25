package com.mercadolibre.desafio_testing.dto.response;

import lombok.AllArgsConstructor;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class RoomSquareMetersResponseDTO {
    private String environment_name;
    private double environment_square_meters;
}
