package com.meli.socialmeli.dtos.response;

import com.meli.socialmeli.models.User;

import java.util.List;

public class FollowedUserListDTO extends User{
    private List<User> followed;
}
