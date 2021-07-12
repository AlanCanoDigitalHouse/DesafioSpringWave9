package com.example.desafiospring.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private String errorMessage;
    private Map<String,String> details;
}
