package com.mercadolibre.calculadorametroscuadrados.dto.Response;

import com.mercadolibre.calculadorametroscuadrados.dto.DistrictDTO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.processing.Generated;
import java.util.List;

@Getter

public class PropertyEnvironmentSquareMetersResponse extends PropertyResponseDTO {

    private List<EnvironmentTotalMetersResponse> environment;

    public PropertyEnvironmentSquareMetersResponse(String nameProperty, DistrictDTO district, List<EnvironmentTotalMetersResponse> environment) {
        super(nameProperty, district);
        this.environment = environment;
    }
}
