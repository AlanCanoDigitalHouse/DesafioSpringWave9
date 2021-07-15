package com.jbianchini.meli.socialmeli.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private int userId;
    private String userName;

    public UserDTO(String userName) {
        this.userName = userName;
    }
}
