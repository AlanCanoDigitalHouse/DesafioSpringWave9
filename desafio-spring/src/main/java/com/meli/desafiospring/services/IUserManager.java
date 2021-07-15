package com.meli.desafiospring.services;

import com.meli.desafiospring.DTOs.request.PromoPostRequestDTO;
import com.meli.desafiospring.DTOs.response.*;
import com.meli.desafiospring.DTOs.request.PostRequestDTO;
import com.meli.desafiospring.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserManager {

    public ResponseEntity<String> follow(Integer userId, Integer userIdToFollow);

    public ResponseEntity<String> unfollow(Integer userId, Integer userIdToUnfollow);

    User getUser(Integer userId);

    FollowersCountResponseDTO followersCount(Integer userId);

    ResponseEntity<FollowersListResponseDTO> followersList(Integer sellerId, String order);
    ResponseEntity followedList(Integer sellerId, String order);

    ResponseEntity<PostsListResponseDTO> lastFollowedPosts(Integer userId, String order);

    ResponseEntity<PostsListResponseDTO> promoPostsList(Integer userId);

    ResponseEntity<List<SimpleUserDTO>> initialize();

    ResponseEntity<PostResponseDTO> newPost(PostRequestDTO postDTO);
    ResponseEntity<PostResponseDTO> newPromoPost(PromoPostRequestDTO promoPostRequestDTO);

    ResponseEntity<PromoPostCountResponseDTO> promoPostCount(Integer userId);
}
