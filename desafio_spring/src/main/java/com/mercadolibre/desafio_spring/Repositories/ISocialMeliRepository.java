package com.mercadolibre.desafio_spring.Repositories;

import com.mercadolibre.desafio_spring.entities.User;

import java.util.Optional;

public interface ISocialMeliRepository {
    void saveUser(User user);
    Optional<User> findUserById(int userId);
    void deleteById(int userId);
    void putUser(User user);

}
