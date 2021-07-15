package com.meli.socialmeli.repository;

import com.meli.socialmeli.dto.PostDTO;
import com.meli.socialmeli.exceptions.DataBaseException;


public interface ProductRepositoryInterface {
   PostDTO obtenerPublicacion(Integer product_id) throws DataBaseException;
}
