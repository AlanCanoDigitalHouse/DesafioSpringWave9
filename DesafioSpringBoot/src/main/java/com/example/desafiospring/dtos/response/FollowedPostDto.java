package com.example.desafiospring.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class FollowedPostDto {
    private Integer userId;
    private List<PostResponseDto> posts;
}
