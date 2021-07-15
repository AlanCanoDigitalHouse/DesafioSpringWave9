package com.example.desafio1.repositories;

import com.example.desafio1.dto.Client;
import com.example.desafio1.dto.User;
import com.example.desafio1.dto.Seller;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements IUserRepository{
    private static final Map<Integer, User> users = initMap();
    private static final List<List<Integer>> userFollows = initUserMatrix();

    private static Map<Integer, User> initMap(){
        Map<Integer, User> users = new HashMap<>();
        users.put(1, new Client(1, "Juanita"));
        users.put(2, new Seller(2, "Jesus"));
        users.put(3, new Client(3, "Eduardo"));
        users.put(4, new Seller(4, "Constanza"));
        users.put(5, new Client(5, "Elisa"));
        users.put(6, new Seller(6, "Diego"));
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

    public boolean isFollowing(Integer followingUserId, Integer followedUserId){
        List<Integer> pair = new ArrayList<>();
        pair.add(followingUserId);
        pair.add(followedUserId);
        return userFollows.contains(pair);
    }
}
