package com.mercadolibre.socialmeli.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO<T> {
    private Integer status;
    private String phrase;
    private String message;
    private T dto;
}
