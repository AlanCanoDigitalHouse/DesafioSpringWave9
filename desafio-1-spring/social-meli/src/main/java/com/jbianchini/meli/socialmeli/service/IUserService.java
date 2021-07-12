package com.jbianchini.meli.socialmeli.service;

import com.jbianchini.meli.socialmeli.exception.ApplicationException;
import com.jbianchini.meli.socialmeli.model.UserDTO;

public interface IUserService {
    UserDTO create(UserDTO user);
    void follow(Integer userId, Integer userIdToFollow) throws ApplicationException;
}
