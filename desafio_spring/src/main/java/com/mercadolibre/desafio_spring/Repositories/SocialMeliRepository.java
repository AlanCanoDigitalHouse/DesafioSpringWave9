package com.mercadolibre.desafio_spring.Repositories;

import com.mercadolibre.desafio_spring.dtos.response.FollowersCountResponse;
import com.mercadolibre.desafio_spring.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class SocialMeliRepository implements ISocialMeliRepository {

    private final Map<Integer, User> database = new HashMap<>();

    @Override
    public void saveUser(User user) {
        if(user.getUserId() == null)
            user.setUserId(database.values().size());
        database.put(user.getUserId(), user);
    }

    @Override
    public Optional<User> findUserById(int userId) {
        User user = database.get(userId);
        return Optional.ofNullable(user);
    }

    @Override
    public void deleteById(int userId) {
        database.remove(userId);
    }

    @Override
    public void putUser(User user) {
        database.remove(user.getUserId());
        saveUser(user);
    }
}
