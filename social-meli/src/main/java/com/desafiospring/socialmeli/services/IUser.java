package com.desafiospring.socialmeli.services;

import com.desafiospring.socialmeli.exceptions.UserException;

public interface IUser {

    void addFollower(int userId, int userIdToFollow) throws UserException;
}
