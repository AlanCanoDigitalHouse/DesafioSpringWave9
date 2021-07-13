package com.jbianchini.meli.socialmeli.repository;

import com.jbianchini.meli.socialmeli.model.Post;
import com.jbianchini.meli.socialmeli.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class PostRepository implements IPostRepository{
    private final Map<Integer, Post> database = new HashMap<>();

    @Override
    public Post save(Post post) {
        if (post.getPostId() == null) {
            post.setPostId(database.values().size());
            post.setProducts(new ArrayList<>());
        }
        database.put(post.getPostId(), post);
        return post;
    }

    @Override
    public Optional<Post> findByPostId(Post postId) {
        Post post = database.get(postId);
        return Optional.ofNullable(post);
    }

    @Override
    public void delete(Post post) {
        database.remove(post.getPostId());
    }
}
