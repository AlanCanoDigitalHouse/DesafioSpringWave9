package com.mercadolibre.desafio.repositories.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.mercadolibre.desafio.dtos.ResponseCountFollowers;
import com.mercadolibre.desafio.dtos.ResponseFollowed;
import com.mercadolibre.desafio.dtos.ResponseFollowers;
import com.mercadolibre.desafio.dtos.ResponseUser;
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
        if (user.getFollowed().contains(userToFollowId)){
            throw new UserException(UserException.USER_ALREADY_FOLLOWED);
        }
        user.getFollowed().add(userToFollow.getUserID());
        userToFollow.getFollowers().add(user.getUserID());
    }

    @Override
    public ResponseCountFollowers countFollowers(Integer userId) throws UserException {
        User user = getUserById(userId);
        Integer count = user.getFollowers().size();
        return new ResponseCountFollowers(user.getUserID(),user.getUserName(),count);
    }

    @Override
    public ResponseFollowers getFollowers(Integer userId) throws UserException {
        User user = getUserById(userId);
        List<ResponseUser> followers = new ArrayList<>();
        for (Integer id: user.getFollowers()){
            followers.add(new ResponseUser(getUserById(id)));
        }

        return new ResponseFollowers(user.getUserID(),user.getUserName(),followers);
    }

    @Override
    public ResponseFollowed getFollowed(Integer userId) throws UserException {
        User user = getUserById(userId);
        List<ResponseUser> followed = new ArrayList<>();
        for (Integer id: user.getFollowed()){
            followed.add(new ResponseUser(getUserById(id)));
        }

        return new ResponseFollowed(user.getUserID(),user.getUserName(),followed);
    }

    @Override
    public User getUserById(Integer userId) throws UserException {
        User user = database.get(userId);
        return Optional.ofNullable(user).orElseThrow(()->new UserException(UserException.ID_NOT_FOUND));
    }
}
