package com.example.demo.dtos.response;

import com.example.demo.dtos.PostDto;
import lombok.Data;

import java.util.List;

@Data
public class PromoPostsFromUserResponseDto {

    private int userId;
    private String userName;
    private List<PostResponseDto> posts;

    public PromoPostsFromUserResponseDto(int userId, String userName, List<PostResponseDto> posts) {
        this.userId = userId;
        this.userName = userName;
        this.posts = posts;
    }
}
