package com.example.socialmeli.repositories;

import com.example.socialmeli.exceptions.UserNotFound;
import com.example.socialmeli.models.User;

import java.util.HashMap;
import java.util.Objects;

public class UserRepository implements IUserRepository{
    static HashMap<Integer, User> users;

    public UserRepository(){
        users = new HashMap<>();
    }

    public User find(Integer userId) throws UserNotFound {
        User user = users.get(userId);
        if(Objects.isNull(user)){
            throw new UserNotFound();
        }
        return users.get(userId);
    }

    public void add(User user) {
        users.put(user.getId(),user);
    }
}
