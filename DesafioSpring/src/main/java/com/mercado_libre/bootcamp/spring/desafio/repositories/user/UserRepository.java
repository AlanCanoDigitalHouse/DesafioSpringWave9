package com.mercado_libre.bootcamp.spring.desafio.repositories.user;

import com.mercado_libre.bootcamp.spring.desafio.models.User;

public interface UserRepository {

    public User getUser(int userId);
}
