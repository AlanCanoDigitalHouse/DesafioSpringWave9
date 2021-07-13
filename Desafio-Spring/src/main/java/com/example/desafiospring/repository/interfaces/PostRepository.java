package com.example.desafiospring.repository.interfaces;

import com.example.desafiospring.DTOS.requests.NewPostRequestDTO;
import com.example.desafiospring.DTOS.responses.PostResponseDTO;

import java.util.List;

public interface PostRepository {
    Integer addPost(NewPostRequestDTO newPostRequestDTO, Integer productId);

    List<PostResponseDTO> getRecentPostsOf(List<Integer> followedIds,String order);
}
