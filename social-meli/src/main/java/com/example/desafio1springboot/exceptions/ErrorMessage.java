package com.example.desafio1springboot.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Map;

@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {

    private Integer status;

    private String error;

    private Map<String, String> message;

    public ErrorMessage(Integer status, String error) {
        this.status = status;
        this.error = error;
    }
}
