package com.jbianchini.meli.socialmeli.repository;

import com.jbianchini.meli.socialmeli.model.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository {
    private final Map<Integer, UserDTO> database = new HashMap<>();

    @Override
    public UserDTO save(UserDTO user) {
        if (user.getUserId() == null) {
            user.setUserId(database.values().size());
        }
        user.setFollowed(new ArrayList<>());
        user.setFollowers(new ArrayList<>());
        database.put(user.getUserId(), user);
        return user;
    }

    @Override
    public Optional<UserDTO> findByUserId(Integer userId) {
        UserDTO user = database.get(userId);
        return Optional.ofNullable(user);
    }

    @Override
    public void delete(UserDTO user) {
        database.remove(user.getUserId());
    }
}
