package com.meli.socialmeli.dtos.response;

import com.meli.socialmeli.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FollowersUserListDTO extends User{
    private List<User> followers;

    public FollowersUserListDTO(User user, List<User> followers) {
        super(user);
        this.followers = followers;
    }
}
