package com.mercadolibre.socialmedia.dtos.response;

import com.mercadolibre.socialmedia.dtos.User;

import java.util.List;

public class FollowedUsersResponse extends User {
    private List<User> followed;

    public FollowedUsersResponse(Integer userId, String userName, List<User> followed){
        super(userId, userName);
        this.followed = followed;
    }

    public List<User> getFollowed(){ return this.followed; }

}
