package com.example.socialmeli.services;

import com.example.socialmeli.dtos.requests.RequestPostDto;
import com.example.socialmeli.dtos.responses.*;
import com.example.socialmeli.exceptions.UserNotFound;

import javax.servlet.http.HttpServletResponse;

public interface ISocialMeliProductServices {

    ResponseRequestDto post(RequestPostDto requestPostDto) throws UserNotFound;

    ResponsePostsListDto getPostsInfo(Integer userId, String order) throws UserNotFound;

}
