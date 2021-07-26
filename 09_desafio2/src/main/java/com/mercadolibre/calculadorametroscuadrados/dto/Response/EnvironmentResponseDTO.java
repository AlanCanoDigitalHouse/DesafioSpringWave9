package com.mercadolibre.calculadorametroscuadrados.dto.Response;

import com.mercadolibre.calculadorametroscuadrados.dto.Request.EnvironmentRequestDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnvironmentResponseDTO extends EnvironmentRequestDTO {
    private Double squareFeet;

    public EnvironmentResponseDTO(String name, Double width, Double length) {
        super(name, width, length);
    }
}
