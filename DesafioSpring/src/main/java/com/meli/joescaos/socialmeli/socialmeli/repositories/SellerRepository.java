package com.meli.joescaos.socialmeli.socialmeli.repositories;

import com.meli.joescaos.socialmeli.socialmeli.exceptions.UserNotFoundException;
import com.meli.joescaos.socialmeli.socialmeli.models.Seller;

public interface SellerRepository {
    Seller findById(int userId);
}
