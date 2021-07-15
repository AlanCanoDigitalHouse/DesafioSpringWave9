package com.jbianchini.meli.socialmeli.repository;

import com.jbianchini.meli.socialmeli.model.Post;
import com.jbianchini.meli.socialmeli.model.User;

import java.util.List;

public interface IPostRepository {
    Post save(Post post);

    Post findByPostId(Integer postId);

    void delete(Post post);

    List<Post> findByUserSinceTwoWeeksAgo(User user);
}
