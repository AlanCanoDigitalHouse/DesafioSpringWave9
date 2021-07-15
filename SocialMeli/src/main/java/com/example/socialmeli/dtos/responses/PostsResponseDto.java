package com.example.socialmeli.dtos.responses;

import com.example.socialmeli.dtos.PostDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PostsResponseDto {
    private Integer userId;
    List<PostDto> posts;
}
