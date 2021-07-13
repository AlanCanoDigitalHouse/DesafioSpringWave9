package com.example.desafiospring.services;

import com.example.desafiospring.dtos.PostCreateDto;
import com.example.desafiospring.dtos.PostDto;
import com.example.desafiospring.dtos.PostFollowedDto;
import com.example.desafiospring.exceptions.DateInvalidException;
import com.example.desafiospring.exceptions.UserNotExistException;

import java.util.List;

public interface IPostService {

    PostDto createPost(PostCreateDto postCreateDto) throws DateInvalidException, UserNotExistException;

    PostFollowedDto getRecentPosts(Long userId, String order) throws UserNotExistException;

}
