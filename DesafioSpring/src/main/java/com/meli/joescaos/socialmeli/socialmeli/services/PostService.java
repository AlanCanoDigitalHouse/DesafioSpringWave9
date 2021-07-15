package com.meli.joescaos.socialmeli.socialmeli.services;

import com.meli.joescaos.socialmeli.socialmeli.dtos.requests.PostRequestDto;
import com.meli.joescaos.socialmeli.socialmeli.dtos.responses.PostsListDto;

import java.text.ParseException;
import java.util.List;

public interface PostService {
    void createPost(PostRequestDto post);
}
