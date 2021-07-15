package com.desafio.demo.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
public class UserResponseDto {

    private int userId;
    private  String userName;
}
