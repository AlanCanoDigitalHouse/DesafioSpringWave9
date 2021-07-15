package com.mercadolibre.socialmedia.services;

import com.mercadolibre.socialmedia.dtos.PostDto;
import com.mercadolibre.socialmedia.dtos.request.PostRequestDto;
import com.mercadolibre.socialmedia.dtos.response.FollowedUsersResponse;
import com.mercadolibre.socialmedia.dtos.response.FollowersQuantityResponse;
import com.mercadolibre.socialmedia.dtos.response.FollowersUsersResponse;
import com.mercadolibre.socialmedia.dtos.response.PostsUserResponse;
import org.springframework.http.ResponseEntity;

public interface ISocialMediaService {
    ResponseEntity followUser(Integer userId, Integer userIdToFollow);

    FollowersQuantityResponse countUserFollowers(int userId);

    FollowersUsersResponse getFollowersUserList(Integer userId, String order);
    FollowedUsersResponse getFollowedUserList(Integer userId, String order);

    ResponseEntity addNewPost(PostRequestDto postDto);

    PostsUserResponse getLastPostsByUser(Integer userId, String order);

    ResponseEntity unfollowUser(Integer parseInt, Integer userToUnfollow);

   // ResponseEntity addNewPostPromo(PostPromoRequestDto postPromoRequestDto);
}
