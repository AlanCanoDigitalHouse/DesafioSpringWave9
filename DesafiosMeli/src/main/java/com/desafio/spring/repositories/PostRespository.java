package com.desafio.spring.repositories;

import com.desafio.spring.dtos.PostDto;

import java.util.List;

public interface PostRespository {
    void newPost(PostDto post);
    List<PostDto> findPostsLasted(Integer userId, String date);
}
