package com.bootcamp.desafio2.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessageDto {

    private Integer status;
    private String message;
    private Map<String, String> fields;

}
