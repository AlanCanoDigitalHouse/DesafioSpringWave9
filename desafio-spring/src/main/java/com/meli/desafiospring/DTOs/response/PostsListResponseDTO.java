package com.meli.desafiospring.DTOs.response;

import com.meli.desafiospring.DTOs.PostDTO;

import java.util.List;

public class PostsListResponseDTO {

    Integer userId;
    List<PostDTO> posts;
}
