package com.bootcamp.desafio2.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@AllArgsConstructor
@Data
@NoArgsConstructor
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

