package com.example.desafio_spring.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    private Integer userId;
    private String userName;
}
