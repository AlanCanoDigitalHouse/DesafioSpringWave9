package com.mercadolibre.socialmeli.repositories;

import com.mercadolibre.socialmeli.dtos.request.PostRequestDTO;
import com.mercadolibre.socialmeli.models.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    void addPost(PostRequestDTO postRequestDTO);

    Optional<Post> findPostByPostId(Integer postId);

    List<Post> findPostsByUserId(Integer userId);


    void removePost(Post post);

}
