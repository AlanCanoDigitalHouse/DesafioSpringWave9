package com.example.socialmeli.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseErrorDto {
    private Integer status;
    private String error;
    private String message;
}
