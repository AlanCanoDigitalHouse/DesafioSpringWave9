package com.example.socialmeli.repositories.implementations;

import com.example.socialmeli.domains.Post;
import com.example.socialmeli.repositories.interfaces.PostRepository;
import org.springframework.stereotype.Repository;

import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostRepositoryImpl implements PostRepository {

    private final AtomicInteger indexPost = new AtomicInteger();

    @Override
    public Post savePost(Post post) {
        post.setIdPost(indexPost.incrementAndGet());
        return post;
    }

}
