package com.mercadolibre.social_meli.repository;

import com.mercadolibre.social_meli.entity.User;

import java.util.List;

public interface IUserRepository {

    User getUser(Integer userId);

    List<User> getUsers();

    void updateAllUsers(List<User> users);

}
