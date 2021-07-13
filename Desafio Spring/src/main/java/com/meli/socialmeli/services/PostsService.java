package com.meli.socialmeli.services;

import com.meli.socialmeli.models.Post;

import java.util.List;

public class PostsService implements IPostsService {
    @Override
    public boolean validatePostId(int id_post) {
        return false;
    }

    @Override
    public List<Post> orderDateAsc(List<Post> posts) {
        return null;
    }

    @Override
    public List<Post> orderDateDesc(List<Post> posts) {
        return null;
    }
}
