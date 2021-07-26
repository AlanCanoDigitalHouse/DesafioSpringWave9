package com.bootcamp.desafio2.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ErrorMessageDto {

    private Integer status;
    private String message;
    private Map<String, String> fields;

    public ErrorMessageDto(int value, String message) {
        this.status = value;
        this.message = message;
    }

    public ErrorMessageDto(int value, String message, Map<String, String> fields) {
        this.status = value;
        this.message = message;
        this.fields = fields;
    }
}
