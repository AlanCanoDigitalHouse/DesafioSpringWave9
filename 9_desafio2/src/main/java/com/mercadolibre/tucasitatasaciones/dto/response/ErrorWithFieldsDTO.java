package com.mercadolibre.tucasitatasaciones.dto.response;

import lombok.Getter;

import java.util.Map;

@Getter
public class ErrorWithFieldsDTO extends ErrorDTO {

    private final Map<String, String> fields;

    public ErrorWithFieldsDTO(Integer status, String message, Map<String, String> fields) {
        super(status, message);
        this.fields = fields;
    }

}
