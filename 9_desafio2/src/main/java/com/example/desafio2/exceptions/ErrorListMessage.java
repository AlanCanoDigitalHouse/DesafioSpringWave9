package com.example.desafio2.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ErrorListMessage {
    private Integer status;
    private String error;
    private List<String> message;
}