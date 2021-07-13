package com.desafiospring.socialmeli.services;

import com.desafiospring.socialmeli.dtos.models.Post;
import com.desafiospring.socialmeli.exceptions.UserException;

public interface IProduct {

    Post addPost(Post post) throws UserException;
}
