package com.example.desafiospring.repository.interfaces;

import com.example.desafiospring.DTOS.requests.NewPostRequestDTO;
import com.example.desafiospring.DTOS.responses.PostResponseDTO;
import com.example.desafiospring.DTOS.responses.PromoPostResponseDTO;

import java.util.List;
import java.util.Set;

public interface PostRepository {
    Integer addPost(NewPostRequestDTO newPostRequestDTO, Integer productId);

    List<PostResponseDTO> getRecentPostsOf(List<Integer> followedIds,String order);

    Set<Integer> getPromoProductIDs(Integer userId);

    List<PromoPostResponseDTO> getPromoPostsOf(Integer userId, String order);
}
