package com.meli.desafiospring.models;

import com.meli.desafiospring.DTOs.PostDTO;
import lombok.Data;

import java.util.List;

@Data
public class User {

    Integer userId;
    String userName;
    List<PostDTO> postDTOS;
    List<User> followers;
    List<User> followed;

    public void follow(User followed) {
        followed.getFollowers().add(this);
    }

    public void unfollow(User unfollowed) {
        if (followed.contains(unfollowed))
            this.followers.remove(unfollowed);
    }
}
