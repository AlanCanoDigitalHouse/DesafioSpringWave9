package com.example.socialmeli.services;

import com.example.socialmeli.dtos.requests.RequestPostDto;
import com.example.socialmeli.dtos.responses.*;
import com.example.socialmeli.exceptions.IncompatibleRequest;
import com.example.socialmeli.exceptions.InvalidOrder;
import com.example.socialmeli.exceptions.UserNotFound;


public interface ISocialMeliProductServices {

    ResponseRequestDto post(RequestPostDto requestPostDto) throws UserNotFound, IncompatibleRequest;

    ResponsePostsListDto getPostsInfo(Integer userId, String order) throws UserNotFound, InvalidOrder;

}
