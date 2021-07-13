package com.example.desafiospring.repository.interfaces;

import com.example.desafiospring.DTOS.requests.NewPostRequestDTO;

public interface PostRepository {
    Integer addPost(NewPostRequestDTO newPostRequestDTO, Integer productId);
}
