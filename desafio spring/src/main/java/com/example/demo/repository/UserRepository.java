package com.example.demo.repository;

import com.example.demo.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository{


    private static Map<Integer , User> users;

    public UserRepository(){
        this.users = new HashMap<>();
    }

    public User getUser(int userId) {
        return this.users.get(userId);
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    public void addUser(int userId , String userName) {
        this.users.put(userId , new User(userId , userName));
    }


}
