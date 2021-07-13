package com.meli.socialmeli.dtos.response;

import com.meli.socialmeli.models.User;

import java.util.List;

public class FollowersUserListDTO extends User{
    private List<User> followers;
}
