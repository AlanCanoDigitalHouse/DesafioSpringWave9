package com.meli.joescaos.socialmeli.socialmeli.repositories;

import com.meli.joescaos.socialmeli.socialmeli.exceptions.UserNotFoundException;
import com.meli.joescaos.socialmeli.socialmeli.models.Buyer;

public interface BuyerRepository {
    Buyer findById(int userId) throws UserNotFoundException;

}
