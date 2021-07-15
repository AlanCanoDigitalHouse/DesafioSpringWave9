package com.example.socialmeli.services;

import com.example.socialmeli.dtos.UserDto;
import com.example.socialmeli.dtos.request.PostDto;
import com.example.socialmeli.dtos.response.FollowedListResponseDto;
import com.example.socialmeli.dtos.response.FollowersCountResponseDto;
import com.example.socialmeli.dtos.response.FollowersListResponseDto;
import com.example.socialmeli.dtos.response.PostResponseDto;
import com.example.socialmeli.exceptions.CantFollowYourselfException;
import com.example.socialmeli.exceptions.OrderNotFoundException;
import com.example.socialmeli.exceptions.UserNotFoundException;
import com.example.socialmeli.models.User;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface IServiceSocialMeli {
    
    void follow(Integer userId, Integer userIdToFollow) throws UserNotFoundException, CantFollowYourselfException;

    List<User> create();

    FollowersCountResponseDto countFollower(Integer userId) throws UserNotFoundException;

    FollowersListResponseDto getFollowers(Integer userID, String order) throws OrderNotFoundException;

    FollowedListResponseDto followedList(Integer userId,String order) throws UserNotFoundException, OrderNotFoundException;

    List<User> unfollow(Integer userId, Integer userIdToUnfollow) throws UserNotFoundException;

    HttpStatus saveNewPost(PostDto postDto) throws UserNotFoundException;

    PostResponseDto getPost(Integer userId, String order) throws UserNotFoundException, OrderNotFoundException;

    List<UserDto> getNameOrder(List<UserDto> ListToOrder, String order) throws OrderNotFoundException;

    List<PostDto> getPostOrdered(List<PostDto> postsToOrder, String order) throws OrderNotFoundException;
}
