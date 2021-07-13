package com.example.desafiospring.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessageDto {

    private Integer status;
    private String message;
    private Map<String, String> fields;

    public ErrorMessageDto(String message) {
        this.message = message;
    }

}
