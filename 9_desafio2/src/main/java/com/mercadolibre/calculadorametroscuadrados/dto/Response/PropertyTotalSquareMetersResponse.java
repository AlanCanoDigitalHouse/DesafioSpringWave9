package com.mercadolibre.calculadorametroscuadrados.dto.Response;

import com.mercadolibre.calculadorametroscuadrados.dto.DistrictDTO;
import lombok.*;


@Getter
public class PropertyTotalSquareMetersResponse extends PropertyResponseDTO{

    private Double squareFeet;

    public PropertyTotalSquareMetersResponse(String nameProperty, DistrictDTO district, Double squareFeet) {
        super(nameProperty, district);
        this.squareFeet = squareFeet;
    }
}
