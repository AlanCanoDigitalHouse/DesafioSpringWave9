package com.example.socialmeli.services;

import com.example.socialmeli.dtos.PostDto;
import com.example.socialmeli.dtos.requests.PostRequestDto;
import com.example.socialmeli.dtos.requests.PromoPostRequestDto;
import com.example.socialmeli.dtos.responses.CountPromoPostResponseDto;
import com.example.socialmeli.dtos.responses.PostsResponseDto;
import com.example.socialmeli.dtos.responses.PromoPostResponseDto;

import java.util.List;

public interface IPostService {
    void newPost(PostRequestDto post);

    void newPromoPost(PromoPostRequestDto post);

    PostsResponseDto getPost(Integer userId,String order);

    PromoPostResponseDto getPromoPost(Integer userId, String order);

    CountPromoPostResponseDto getCountPromoPost(Integer userId);
}
