package com.mercadolibre.social_meli.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ErrorFieldsResponse extends ErrorResponse {

    private Map<String, String> fields;

    public ErrorFieldsResponse(String message, Integer code, Map<String, String> fields) {
        super(message, code);
        this.fields = fields;
    }

}
