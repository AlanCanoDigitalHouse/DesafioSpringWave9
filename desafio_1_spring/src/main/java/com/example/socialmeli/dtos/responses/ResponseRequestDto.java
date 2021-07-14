package com.example.socialmeli.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseRequestDto {
    private int status;
    private String phrase;
    private String message;
}
