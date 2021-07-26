package com.mercadolibre.calculadorametroscuadrados.dto.Response;

import com.mercadolibre.calculadorametroscuadrados.dto.DistrictDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.EnvironmentDTO;
import lombok.*;

@Getter
public class PropertyEnvironmentBiggerResponse extends PropertyResponseDTO {
     public EnvironmentDTO bigger;

    public PropertyEnvironmentBiggerResponse(String nameProperty, DistrictDTO district, EnvironmentDTO bigger) {
        super(nameProperty, district);
        this.bigger = bigger;
    }
}
