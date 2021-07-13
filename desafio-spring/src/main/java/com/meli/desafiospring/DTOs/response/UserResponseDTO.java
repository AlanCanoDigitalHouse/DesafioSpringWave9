package com.meli.desafiospring.DTOs.response;

public class UserResponseDTO {

    Integer userId;
    String userName;

    public UserResponseDTO(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

}
