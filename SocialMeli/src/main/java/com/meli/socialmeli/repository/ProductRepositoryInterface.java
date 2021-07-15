package com.meli.socialmeli.repository;

import com.meli.socialmeli.dto.PostDTO;
import com.meli.socialmeli.dto.UserDTO;
import com.meli.socialmeli.exceptions.DataBaseException;
import com.meli.socialmeli.exceptions.UserNullException;
import org.springframework.http.ResponseEntity;

public interface ProductRepositoryInterface {
    public PostDTO obtenerPublicacion(Integer product_id) throws DataBaseException;
}
