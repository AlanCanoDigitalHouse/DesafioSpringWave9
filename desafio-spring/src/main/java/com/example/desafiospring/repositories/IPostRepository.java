package com.example.desafiospring.repositories;

import com.example.desafiospring.dtos.PostCreateDto;
import com.example.desafiospring.entities.Post;

import java.util.List;

public interface IPostRepository {

    Post createPost(Post post);

    List<Post> getPostsByUsersId(List<Long> usersId);

}
