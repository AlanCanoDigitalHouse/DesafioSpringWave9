package com.example.desafiospring.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostsUserResponseDTO {

    private int userId;
    List<PostResponseDTO> posts;

    public PostsUserResponseDTO(int pUserId) {
        userId = pUserId;
    }
}
