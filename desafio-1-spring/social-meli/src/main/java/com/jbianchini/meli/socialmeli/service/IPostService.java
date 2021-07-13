package com.jbianchini.meli.socialmeli.service;

import com.jbianchini.meli.socialmeli.dto.request.NewPostRequest;
import com.jbianchini.meli.socialmeli.model.Post;

public interface IPostService {
    Post newPost(NewPostRequest newPostRequest);

}
