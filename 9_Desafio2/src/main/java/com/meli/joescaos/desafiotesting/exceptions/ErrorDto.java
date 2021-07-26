package com.meli.joescaos.desafiotesting.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDto {
    private int status;
    private String message;
}
