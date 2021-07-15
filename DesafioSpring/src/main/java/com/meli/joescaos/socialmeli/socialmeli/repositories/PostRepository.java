package com.meli.joescaos.socialmeli.socialmeli.repositories;

import com.meli.joescaos.socialmeli.socialmeli.models.Post;

import java.util.List;

public interface PostRepository {

    void savePost(Post post);

    List<Post> getPosts();

}
