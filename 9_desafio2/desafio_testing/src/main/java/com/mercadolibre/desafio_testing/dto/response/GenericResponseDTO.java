package com.mercadolibre.desafio_testing.dto.response;

import lombok.*;

@Getter
@Setter
public abstract class GenericResponseDTO {
    private String response;

    public GenericResponseDTO(String response) {
        this.response = response;
    }
}
