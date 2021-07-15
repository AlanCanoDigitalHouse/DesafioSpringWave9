package com.mercadolibre.socialmeli.services;

import com.mercadolibre.socialmeli.dtos.request.PostRequestDTO;
import com.mercadolibre.socialmeli.dtos.response.UserFollowedLatestPostsResponseDTO;

public interface PostService {


    void addPost(PostRequestDTO postRequestDTO);

    UserFollowedLatestPostsResponseDTO followedLatestPosts(Integer userId, String order);
}
