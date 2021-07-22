package com.api.firstspringchallenge.repositories.user;

import com.api.firstspringchallenge.models.User;

import java.util.List;

public interface SellerRepositoryI {

    boolean isSeller(int userId);

    User findUserById(int userId);

    List<User> getUsers();

}
