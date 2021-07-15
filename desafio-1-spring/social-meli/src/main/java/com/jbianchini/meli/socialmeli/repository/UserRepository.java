package com.jbianchini.meli.socialmeli.repository;

import com.jbianchini.meli.socialmeli.exception.UserNotFoundException;
import com.jbianchini.meli.socialmeli.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
        database.put(user.getUserId(), user);
        return user;
    }

    @Override
    public User findByUserId(Integer userId) {
        User user = database.get(userId);
        if (Objects.nonNull(user)) {
            return user;
        } else {
            throw new UserNotFoundException("User with id " + userId + " was not found in the database.");
        }
    }

    @Override
    public void delete(User user) {
        database.remove(user.getUserId());
    }
}
