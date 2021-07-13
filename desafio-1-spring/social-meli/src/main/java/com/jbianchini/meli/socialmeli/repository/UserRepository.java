package com.jbianchini.meli.socialmeli.repository;

import com.jbianchini.meli.socialmeli.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository {
    private final Map<Integer, User> database = new HashMap<>();

    @Override
    public User save(User user) {
        if (user.getUserId() == null) {
            user.setUserId(database.values().size());
            user.setFollowed(new ArrayList<>());
            user.setFollowers(new ArrayList<>());
        }
        //TODO: make the database to just update the user if exists
        database.put(user.getUserId(), user);
        return user;
    }

    @Override
    public Optional<User> findByUserId(Integer userId) {
        //TODO: Throw exception here if the user was not found
        User user = database.get(userId);
        return Optional.ofNullable(user);
    }

    @Override
    public void delete(User user) {
        database.remove(user.getUserId());
    }
}
