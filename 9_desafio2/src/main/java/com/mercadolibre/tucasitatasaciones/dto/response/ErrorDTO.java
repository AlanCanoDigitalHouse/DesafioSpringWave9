package com.mercadolibre.tucasitatasaciones.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDTO {

    private final Integer status;
    private final String message;

}
