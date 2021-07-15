package com.meli.socialmeli.repository;

import com.meli.socialmeli.dto.UserDTO;
import com.meli.socialmeli.exceptions.DataBaseException;
import com.meli.socialmeli.exceptions.UserNullException;

public interface UserRepositoryInterface {
    UserDTO obtenerUsuario(Integer userId) throws UserNullException, DataBaseException;
}
