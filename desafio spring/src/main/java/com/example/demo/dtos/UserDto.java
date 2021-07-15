package com.example.demo.dtos;

import lombok.Data;

@Data
public class UserDto {
    protected int userId;
    protected String userName;

    public UserDto(int userId, String name) {
        this.userId = userId;
        this.userName = name;
    }
}
