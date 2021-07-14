package com.meli.socialmeli.dtos.response;

import com.meli.socialmeli.models.Post;
import com.meli.socialmeli.models.User;
import lombok.Getter;

import java.util.List;

@Getter
public class FollowedPostListDTO extends User {
    private List<PostDTO> posts;

    public FollowedPostListDTO(User user, List<PostDTO> posts) {
        super(user);
        this.posts = posts;
    }
}
