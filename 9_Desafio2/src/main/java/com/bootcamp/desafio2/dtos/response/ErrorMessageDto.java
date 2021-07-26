package com.bootcamp.desafio2.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class ErrorMessageDto {

    private Integer status;
    private String message;
    private Map<String, String> fields;

}
