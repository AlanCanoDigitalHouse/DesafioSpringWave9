package com.example.demo.dtos.response;

import com.example.demo.dtos.PostDto;
import lombok.Data;

import java.util.List;

@Data
public class PostsListResponseDto {

    private int userId;
    private List<PostResponseDto> posts;

    public PostsListResponseDto(int userId, List<PostResponseDto> posts) {
        this.userId = userId;
        this.posts = posts;
    }
}
