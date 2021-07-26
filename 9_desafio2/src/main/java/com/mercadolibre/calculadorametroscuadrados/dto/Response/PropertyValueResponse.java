package com.mercadolibre.calculadorametroscuadrados.dto.Response;

import com.mercadolibre.calculadorametroscuadrados.dto.DistrictDTO;
import lombok.*;


@Getter

public class PropertyValueResponse extends  PropertyResponseDTO{
    Double priceProperty;

    public PropertyValueResponse(String nameProperty, DistrictDTO district, Double priceProperty) {
        super(nameProperty, district);
        this.priceProperty = priceProperty;
    }
}
