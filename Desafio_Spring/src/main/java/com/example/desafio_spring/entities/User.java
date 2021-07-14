package com.example.desafio_spring.entities;

import com.example.desafio_spring.dtos.request.UserRequestDTO;
import com.example.desafio_spring.dtos.response.UserResponseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private Integer userId;
    private String userName;
    private Integer follower_count;
    private ArrayList<UserResponseDTO> followers;
    private ArrayList<UserResponseDTO> followed;

    public User(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.follower_count = 0;
        this.followers = new ArrayList<>();
        this.followed = new ArrayList<>();
    }
    //Constructor para retornar solo userId, userName, followerCount
    public User(Integer userId, String userName, Integer follower_count) {
        this.userId = userId;
        this.userName = userName;
        this.follower_count = follower_count;
    }

    public User(Integer userId, String userName, ArrayList<UserResponseDTO> followers) {
        this.userId = userId;
        this.userName = userName;
        this.followers = followers;
    }

    public User(Integer userId, String userName, ArrayList<UserResponseDTO> followed, boolean aux) {
        this.userId = userId;
        this.userName = userName;
        this.followed = followed;
    }
}
