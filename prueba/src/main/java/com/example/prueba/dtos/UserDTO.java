package com.example.prueba.dtos;

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
