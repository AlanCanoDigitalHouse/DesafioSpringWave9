package com.mercadolibre.calculadorametroscuadrados.dto.Response;

import com.mercadolibre.calculadorametroscuadrados.dto.DistrictDTO;
import lombok.*;


@Getter

@AllArgsConstructor
public class PropertyResponseDTO {
    private String nameProperty;
    private DistrictDTO district;
}
