package com.example.socialmeli.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class PostResponseDTO {

    private Integer userId;
    private List<PostDetailResponseDTO> posts;

    public PostResponseDTO(){
        this.posts = new ArrayList<>();
    }

}
