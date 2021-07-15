package com.desafio.spring.services;

import com.desafio.spring.dtos.PostDto;

import java.util.List;

public interface PostService {
    void newPost(PostDto post);

    List<PostDto> getPostSeller(Integer userId, String date);

}
