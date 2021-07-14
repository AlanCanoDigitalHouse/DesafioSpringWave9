package com.example.desafio1springboot.dtos.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPostsResposeDTO {
    private Integer userId;
    private List<PostResponseDTO> posts;

    public UserPostsResposeDTO(Integer userId) {
        this.userId = userId;
    }
}
