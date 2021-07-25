package com.mercadolibre.desafio_testing.dto.response;

import com.mercadolibre.desafio_testing.exception.GenericCustomException;
import lombok.*;

@Getter
@Setter
public class GenericErrorResponseDTO extends GenericResponseDTO {
    public GenericErrorResponseDTO(GenericCustomException e) {
        super(e.getMessage());
    }

    public GenericErrorResponseDTO(String response) {
        super(response);
    }
}
