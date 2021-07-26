package com.mercadolibre.calculadorametroscuadrados.dto.Response;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.Request.HouseRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HouseResponseDTO extends HouseDTO {

    private Double squareFeet;
    private Double price;
    private EnvironmentResponseDTO biggest;
    private List<EnvironmentResponseDTO> environments;

    public HouseResponseDTO(HouseRequestDTO house) {
        this.setName(house.getName());
        this.setDistrict(house.getDistrict());
    }

}
