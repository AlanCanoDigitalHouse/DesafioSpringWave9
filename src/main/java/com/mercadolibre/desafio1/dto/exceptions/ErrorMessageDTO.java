package com.mercadolibre.desafio1.dto.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessageDTO {
    private Integer status;
    private String error;
    private Map<String,String> message;

    public ErrorMessageDTO(Integer status, String error, Map<String, String> message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public ErrorMessageDTO(Integer status, String error) {
        this.status = status;
        this.error = error;
    }
}
