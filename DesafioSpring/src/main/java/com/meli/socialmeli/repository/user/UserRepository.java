package com.meli.socialmeli.repository.user;

import com.meli.socialmeli.exception.UserNotFoundException;
import com.meli.socialmeli.model.User;

public interface UserRepository {
  User getUser(Integer userId) throws UserNotFoundException;
}
