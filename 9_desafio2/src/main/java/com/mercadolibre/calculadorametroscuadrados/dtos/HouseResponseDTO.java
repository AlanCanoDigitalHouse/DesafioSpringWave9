package com.mercadolibre.calculadorametroscuadrados.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class HouseResponseDTO extends HouseDTO {
    private Double squareFeet;
    private Double price;
    private EnvironmentDTO biggest;

    public HouseResponseDTO(HouseDTO house) {
        this.setProp_name(house.getProp_name());
        this.setEnvironments(house.getEnvironments());
    }
}
