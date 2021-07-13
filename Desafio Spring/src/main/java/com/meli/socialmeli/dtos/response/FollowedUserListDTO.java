package com.meli.socialmeli.dtos.response;

import com.meli.socialmeli.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FollowedUserListDTO extends User{
    private List<User> followed;

    public FollowedUserListDTO(User user, List<User> followed) {
        super(user);
        this.followed = followed;
    }
}
