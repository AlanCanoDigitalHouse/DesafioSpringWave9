package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.Data;

import java.util.List;
@Data

public class HouseSizesResponseDTO {

    private String prop_name;
    private List<RoomCountResponseDTO> environment_sizes;

}
