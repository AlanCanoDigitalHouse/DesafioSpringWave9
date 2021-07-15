package com.example.desafiospring.repositories;

import com.example.desafiospring.dtos.PostDTO;
import com.example.desafiospring.exceptions.UserNotFoundByIdException;

public interface IPostRepository {

    PostDTO findPostById(Integer postId);

    void newPost(PostDTO postDTO) throws UserNotFoundByIdException;

}
