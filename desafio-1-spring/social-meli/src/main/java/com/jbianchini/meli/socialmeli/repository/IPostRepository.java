package com.jbianchini.meli.socialmeli.repository;

import com.jbianchini.meli.socialmeli.model.Post;

import java.util.Optional;

public interface IPostRepository {
    Post save(Post post);

    Optional<Post> findByPostId(Post postId);

    void delete(Post post);
}
