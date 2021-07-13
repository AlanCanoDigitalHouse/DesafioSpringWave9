package com.meli.socialmeli.services;

import com.meli.socialmeli.models.Post;

import java.util.List;

public interface IPostsService {
    boolean validatePostId(int id_post);
    List<Post> orderDateAsc(List<Post> posts);
    List<Post> orderDateDesc(List<Post> posts);
}
