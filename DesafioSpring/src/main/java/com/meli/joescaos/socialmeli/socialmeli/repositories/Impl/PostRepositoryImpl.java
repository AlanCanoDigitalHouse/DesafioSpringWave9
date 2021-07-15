package com.meli.joescaos.socialmeli.socialmeli.repositories.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.joescaos.socialmeli.socialmeli.exceptions.PostNotFoundException;
import com.meli.joescaos.socialmeli.socialmeli.models.Post;
import com.meli.joescaos.socialmeli.socialmeli.repositories.PostRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class PostRepositoryImpl implements PostRepository {

    // Attributes
    private List<Post> posts;

    /*
    * Constructor
     */
    public PostRepositoryImpl() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            posts = Arrays.asList(mapper.readValue(new ClassPathResource("static/posts.json").getFile(), Post[].class));
        } catch (IOException e) {
            e.printStackTrace();
            posts = new ArrayList<>();
        }
    }

    /**
     *
     * @param post
     */
    @Override
    public void savePost(Post post) {
        if (!posts.contains(post))
            posts = new ArrayList<>(posts);
    }

    /**
     *
     * @return List of posts
     */
    @Override
    public List<Post> getPosts() {
        return posts;
    }
}
