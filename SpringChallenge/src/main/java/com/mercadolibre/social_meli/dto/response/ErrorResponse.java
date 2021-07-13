package com.mercadolibre.social_meli.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private Integer code;
    private Map<String, String> fields;

}
