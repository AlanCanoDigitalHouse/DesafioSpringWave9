package com.example.desafio1.dto.response;

import com.example.desafio1.dto.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ListFollowedPostsResponseDTO {
    private Integer userId;
    private List<Post> posts;
}
