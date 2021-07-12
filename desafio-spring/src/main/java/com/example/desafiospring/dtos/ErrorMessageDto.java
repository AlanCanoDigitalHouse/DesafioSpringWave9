package com.example.desafiospring.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ErrorMessageDto {

    private Integer status;
    private String message;
    private Map<String, String> fields;

    public ErrorMessageDto(String message) {
        this.message = message;
    }

}
