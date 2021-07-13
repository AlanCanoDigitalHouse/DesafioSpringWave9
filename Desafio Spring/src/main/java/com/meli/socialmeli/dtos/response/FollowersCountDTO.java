package com.meli.socialmeli.dtos.response;

import com.meli.socialmeli.models.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FollowersCountDTO extends User {
    private long followers_count;

    public FollowersCountDTO(User user, long count){
        super(user);
        this.followers_count = count;
    }
}
