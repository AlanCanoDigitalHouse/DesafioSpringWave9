package com.meli.desafiospring.DTOs.response;

import lombok.Data;

@Data
public class UserResponseDTO {

    private Integer userId;
    private String userName;

    public UserResponseDTO(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

}
