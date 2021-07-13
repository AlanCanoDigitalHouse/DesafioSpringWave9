package com.meli.socialmeli.services;

import com.meli.socialmeli.models.User;

import java.util.List;

public interface IUsersService {
    boolean validateUserId(int userId);
    List<User> orderNameAsc(List<User> users);
    List<User> orderNameDesc(List<User> users);
}
