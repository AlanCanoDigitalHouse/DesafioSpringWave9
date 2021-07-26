package com.mercadolibre.calculadorametroscuadrados.helpers;

import com.mercadolibre.calculadorametroscuadrados.dto.Request.EnvironmentRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.Response.EnvironmentResponseDTO;

public class EnvironmentHelper {

    /**
     * Mapper between product model objects and DTO products response
     *
     * @param request DTO request to be mapped to model
     * @return model product
     */
    public static EnvironmentResponseDTO mapper(EnvironmentRequestDTO request) {
        return new EnvironmentResponseDTO(
                request.getName(),
                request.getWidth(),
                request.getLength()
        );
    }
}
