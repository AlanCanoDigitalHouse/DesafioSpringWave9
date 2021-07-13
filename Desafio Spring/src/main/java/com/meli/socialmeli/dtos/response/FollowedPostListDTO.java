package com.meli.socialmeli.dtos.response;

import com.meli.socialmeli.models.Post;
import com.meli.socialmeli.models.User;

import java.util.List;

public class FollowedPostListDTO extends User {
    private List<Post> posts;

    public FollowedPostListDTO(User user) {
        super(user);
    }
}
