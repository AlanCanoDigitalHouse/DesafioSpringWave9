package com.meli.itbootcamp.repositories;

import com.meli.itbootcamp.model.Post;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostRepostoryImpl implements PostRespository {
    private final Map<Integer, Post> MeliPost = new HashMap<>();
    private final AtomicInteger postInx= new AtomicInteger();

    @Override

    public Post addNewPost(Post post) {
        post.setId_post(postInx.incrementAndGet());

        MeliPost.put(post.getId_post(), post);

        return post;
    }
}
