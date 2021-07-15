package com.mercadolibre.socialmeli.repositories;

import com.mercadolibre.socialmeli.models.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final Map<Integer, User> database = new HashMap<>();


    @Override
    public User addUser(User user) {
        if (user.getUserID() == null)
            user.setUserID(database.values().size());
        database.put(user.getUserID(), user);
        return user;
    }

    @Override
    public Optional<User> findUserByUserId(Integer userId) {
        User user = database.get(userId);
        return Optional.ofNullable(user);
    }

    @Override
    public void removeUser(User user) {
        database.remove(user.getUserID());
    }


}

