package com.meli.itbootcamp.repositories;

import com.meli.itbootcamp.model.User;
import com.meli.itbootcamp.model.UserNonSeller;
import com.meli.itbootcamp.model.UserSeller;

import java.util.List;

public interface UserRepository {

    UserSeller saveUser(UserSeller toSave);
    UserNonSeller saveUser(UserNonSeller toSave);

    List<User> findAll();

    UserSeller findUserSellerById(Integer userId);
    UserNonSeller findUserNonSellerById(Integer userId);

    User findUserByName(String userName);

}
