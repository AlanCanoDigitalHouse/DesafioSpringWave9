package com.example.desafio1.repositories;

import com.example.desafio1.dto.Client;
import com.example.desafio1.dto.User;
import com.example.desafio1.dto.Seller;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements IUserRepository{
    private static Map<Integer, User> users = initMap();
    private static List<List<Integer>> userFollows = initUserMatrix();

    private static Map<Integer, User> initMap(){
        Map<Integer, User> users = new HashMap<>();
        users.put(1235, new Client(1235, "Juanita"));
        users.put(1569, new Seller(1569, "Jesus"));
        return users;
    }

    //matrix where each row is a (follower, followed)pair
    private static List<List<Integer>>  initUserMatrix(){
        return new ArrayList<>();
    }

    //In this map, the first value is the follower
    @Override
    public void addNewFollower(Integer userId, Integer userIdToFollow) {
        List<Integer> pair = new ArrayList<>();
        pair.add(userId);
        pair.add(userIdToFollow);
        userFollows.add(pair);
    }

    @Override
    public void unfollow(Integer userId, Integer userIdToUnfollow) {
        List<Integer> pair = new ArrayList<>();
        pair.add(userId);
        pair.add(userIdToUnfollow);
        userFollows.removeIf(e -> e.equals(pair));
    }

    @Override
    public User findUserById(Integer userId) {
        return users.get(userId);
    }

    @Override
    public List<User> listFollowers(Integer userId) {
        return userFollows.stream()
                .filter(entry -> entry.get(1).equals(userId))
                .map(e -> findUserById(e.get(0)))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> listFollowed(Integer userId) {
        return userFollows.stream()
                .filter(entry -> entry.get(0).equals(userId))
                .map(e -> findUserById(e.get(1)))
                .collect(Collectors.toList());
    }
}
