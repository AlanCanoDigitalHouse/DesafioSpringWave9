package com.socialmeli.services;

import com.socialmeli.dtos.requests.PostRequestDTO;
import com.socialmeli.dtos.requests.PromoPostRequestDTO;
import com.socialmeli.dtos.responses.*;

public interface SocialMeliService {

    void follow(Integer userId, Integer userToFollowId);
    CountFollowersResponseDTO countFollowers(Integer userId);
    FollowersResponseDTO getFollowers(Integer userId);
    FollowedResponseDTO getFollowed(Integer userId);
    FollowersResponseDTO getFollowers(Integer userId, String order);
    FollowedResponseDTO getFollowed(Integer userId, String order);
    void createPost(PostRequestDTO request);
    LastProductsResponseDTO getLastProducts(Integer userId);
    LastProductsResponseDTO getLastProducts(Integer userId, String order);
    void unfollow(Integer userId, Integer userToUnfollowId);
    void createPostPromo(PromoPostRequestDTO request);
    CountPromoResponseDTO countPromo(Integer userId);
    ListPromoResponseDTO listPromo(Integer userId);


}
