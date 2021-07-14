package com.example.desafiospring.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ErrorListMessage {
    private Integer status;
    private String error;
    private Map<String,String> message;
}
