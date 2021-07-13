package com.example.desafio1springboot.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    private Integer userId;
    private String userName;

    public UserDTO(Integer userId) {
        this.userId = userId;
    }
}
