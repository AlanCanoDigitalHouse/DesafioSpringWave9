package com.desafiospring.socialmeli.services;

import com.desafiospring.socialmeli.dtos.models.Post;
import com.desafiospring.socialmeli.dtos.requests.PostRequestDTO;
import com.desafiospring.socialmeli.dtos.responses.PostListDto;
import com.desafiospring.socialmeli.exceptions.UserException;

public interface IProduct {

    Post addPost(PostRequestDTO post) throws UserException;

    PostListDto getFollowedSellersPost(int userId, String order) throws UserException;
}
