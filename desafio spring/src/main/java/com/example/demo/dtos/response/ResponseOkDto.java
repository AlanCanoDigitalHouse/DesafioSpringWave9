package com.example.demo.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseOkDto {
    private int statusCode;
    private String message;
}
