package com.example.desafiospring.repository.interfaces;

import com.example.desafiospring.DTOS.requests.NewPostRequestDTO;
import com.example.desafiospring.entities.PostEntity;

import java.util.List;
import java.util.Set;

public interface PostRepository {
    Integer addPost(NewPostRequestDTO newPostRequestDTO, Integer productId);

    List<PostEntity> getRecentPostsOf(List<Integer> followedIds, String order);

    Set<Integer> getPromoProductIDs(Integer userId);

    List<PostEntity> getPromoPostsOf(Integer userId);
}
