package com.example.socialmeli.repositories;

import com.example.socialmeli.dtos.PostDto;

import java.util.List;

public interface IPostRepository {
    List<PostDto> getPosts();

    void saveFile();
}
