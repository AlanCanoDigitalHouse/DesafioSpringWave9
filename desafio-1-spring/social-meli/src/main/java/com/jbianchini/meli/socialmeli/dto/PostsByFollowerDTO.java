package com.jbianchini.meli.socialmeli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PostsByFollowerDTO {
    private Integer userId;
    private List<PostDTO> posts;
}
