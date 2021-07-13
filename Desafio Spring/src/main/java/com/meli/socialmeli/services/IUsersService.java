package com.meli.socialmeli.services;

import com.meli.socialmeli.exceptions.UserDoesNotExistException;
import com.meli.socialmeli.models.User;

import java.util.List;

public interface IUsersService {
    void follow(int followedUserId, int followerUserId) throws UserDoesNotExistException;
    List<User> orderNameAsc(List<User> users);
    List<User> orderNameDesc(List<User> users);
}
