package com.mercadolibre.desafio.repositories.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.mercadolibre.desafio.exception.UserException;
import com.mercadolibre.desafio.model.User;
import com.mercadolibre.desafio.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserInMemoryRepository implements UserRepository {

    Map<Integer, User> database = new HashMap<>();

    public UserInMemoryRepository(){

        User user0 = new User(0,"juan", new ArrayList<>(), new ArrayList<>());
        User user1 = new User(1,"carlos", new ArrayList<>(), new ArrayList<>());
        User user2 = new User(2,"pedro",new ArrayList<>(),new ArrayList<>());
        database.put(0,user0);
        database.put(1,user1);
        database.put(2,user2);

    }

    @Override
    public void follow(Integer userID, Integer userToFollowId) throws UserException {
        User user = getUserById(userID);
        User userToFollow= getUserById(userToFollowId);
        user.getFollowed().add(userToFollow.getUserID());
        userToFollow.getFollowers().add(user.getUserID());
        System.out.println(user.getFollowed());
    }

    @Override
    public User getUserById(Integer userId) throws UserException {
        User user = database.get(userId);
        return Optional.ofNullable(user).orElseThrow(()->new UserException(UserException.ID_NOT_FOUND));
    }
}
