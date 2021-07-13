package com.example.desafiospring.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserBasicDto {

    private Long userId;
    private String userName;

}
