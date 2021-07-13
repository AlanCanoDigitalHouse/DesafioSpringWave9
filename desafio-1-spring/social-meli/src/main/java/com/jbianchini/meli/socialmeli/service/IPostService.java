package com.jbianchini.meli.socialmeli.service;

import com.jbianchini.meli.socialmeli.dto.request.NewPostRequestDTO;
import com.jbianchini.meli.socialmeli.model.Post;

public interface IPostService {
    Post newPost(NewPostRequestDTO newPostRequestDTO);

}
