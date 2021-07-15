package com.jbianchini.meli.socialmeli.service;

import com.jbianchini.meli.socialmeli.dto.PostDTO;
import com.jbianchini.meli.socialmeli.dto.PostsByFollowerDTO;
import com.jbianchini.meli.socialmeli.dto.response.ResponseDTO;

public interface IPostService {
    ResponseDTO newPost(PostDTO postDTO);

    PostsByFollowerDTO getFollowedPostsByUserId(Integer userId, String order);
}
