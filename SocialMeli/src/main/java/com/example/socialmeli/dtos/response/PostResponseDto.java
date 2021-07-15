package com.example.socialmeli.dtos.response;

import com.example.socialmeli.dtos.request.PostDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostResponseDto {

    private Integer userId;
    private List<PostDto> posts;
}
