package com.example.desafio_spring.dtos.response;

import com.example.desafio_spring.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class UserPostResponseDTO {
    private Integer userId;
    private ArrayList<Post> posts;
}
