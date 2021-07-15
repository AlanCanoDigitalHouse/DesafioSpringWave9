package com.example.desafio1.dto.response;

import com.example.desafio1.dto.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ListUsersResponseDTO {
    private Integer userId;
    private String userName;
    private List<User> followers;
}
