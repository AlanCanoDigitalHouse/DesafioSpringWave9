package com.api.firstspringchallenge.dtos.response;

import com.api.firstspringchallenge.models.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class PostsResponseDTO {
    private int userId;
    private List<Post> posts;
}
