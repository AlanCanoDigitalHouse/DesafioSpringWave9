package com.mercado_libre.bootcamp.spring.desafio.repositories.user;

import com.mercado_libre.bootcamp.spring.desafio.models.User;

import java.util.List;

public interface UserRepository {

    public User getUser(int userId);

    public List<User> getUsers();
}
