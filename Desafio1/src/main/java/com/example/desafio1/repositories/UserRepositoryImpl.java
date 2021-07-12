package com.example.desafio1.repositories;

import com.example.desafio1.dto.Client;
import com.example.desafio1.dto.User;
import com.example.desafio1.dto.Vendor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements IUserRepository{
    private static Map<Integer, User> users = initMap();
    private static Map<Integer, Integer> userFollows = new HashMap<>();

    private static Map<Integer, User> initMap(){
        Map<Integer, User> users = new HashMap<>();
        users.put(1235, new Client(1235, "Juanita"));
        users.put(1569, new Vendor(1569, "Jesus"));
        return users;
    }

    //In this map, the first value is the follower
    @Override
    public void addNewFollower(Integer userId, Integer userIdToFollow) {
        userFollows.put(userId, userIdToFollow);
    }

    @Override
    public User findUserById(Integer userId) {
        return users.get(userId);
    }

    @Override
    public List<User> listFollowers(Integer userId) {
        Set<Map.Entry<Integer, Integer>> entries = userFollows.entrySet();
        return entries.stream().filter(entry -> entry.getValue() == userId)
                .map(e -> findUserById(e.getKey()))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> listFollows(Integer userId) {
        Set<Map.Entry<Integer, Integer>> entries = userFollows.entrySet();
        return entries.stream().filter(entry -> entry.getKey() == userId)
                .map(e -> findUserById(e.getValue()))
                .collect(Collectors.toList());
    }
}
