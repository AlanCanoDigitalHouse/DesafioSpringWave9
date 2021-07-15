package com.example.demo.Repositories;

import com.example.demo.Exceptions.ExceptionHandler;
import com.example.demo.Exceptions.NoFollowersException;
import com.example.demo.Exceptions.NoFollowingException;
import com.example.demo.Handlers.ValidateRelation;
import com.example.demo.Handlers.ValidateUser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import com.example.demo.Exceptions.InvalidUserException;
import com.example.demo.Models.UserModel;
import com.example.demo.Models.UserRelation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements IUserRepository {

    private List<UserModel> users = loadUsers();
    private List<UserRelation> usersRelations = loadRelations();
    private int counterRelations = usersRelations.size();

    // "Base de datos"

    private List<UserModel> loadUsers() {
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:users.json");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<UserModel>> typeRef = new TypeReference<>() {};

        List<UserModel> dataBase = null;

        try {
            dataBase = objectMapper.readValue(file, typeRef);
        } catch (Exception e){
            e.printStackTrace();
        }
        return dataBase;
    }

    private List<UserRelation> loadRelations() {
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:relations.json");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<UserRelation>> typeRef = new TypeReference<>() {};

        List<UserRelation> dataBase = null;

        try {
            dataBase = objectMapper.readValue(file, typeRef);
        } catch (Exception e){
            e.printStackTrace();
        }
        return dataBase;
    }

    //

    @Override
    public List<UserModel> getUsers() {
        List<UserModel> users = this.users;
        return users;
    }

    @Override
    public List<UserRelation> getUsersRelations() {
        List<UserRelation> relations = this.usersRelations;
        return relations;
    }

    //

    @Override
    public UserModel getUserById(int userId) throws ExceptionHandler {
        for(UserModel u:users){
            if(u.getUserId() == userId){
                return u;
            }
        }
        throw new InvalidUserException();
    }

    //

    @Override
    public void addFollower(int follower, int following) throws ExceptionHandler {
        boolean validFollower = ValidateUser.validateUser(users, follower);
        boolean validFollowing = ValidateUser.validateUser(users, following);
        ValidateRelation.ValidateRelation(usersRelations, follower, following);
        if(validFollower && validFollowing){
            counterRelations++;
            usersRelations.add(new UserRelation(counterRelations, follower, following));
        }
    }

    @Override
    public void removeFollower(int userId, int unfollowing) throws ExceptionHandler {
        boolean validFollower = ValidateUser.validateUser(users, userId);
        boolean validFollowing = ValidateUser.validateUser(users, unfollowing);
        ValidateRelation.ValidateRelation(usersRelations, userId, unfollowing);
        int relationId = 0;
        if (validFollower && validFollowing) {
            for (UserRelation r:usersRelations) {
                if (r.getFollower() == userId && r.getFollowing() == unfollowing) {
                    relationId = r.getRelationId();
                    this.usersRelations.remove(relationId);
                    break;
                }
            }
        }
    }

    //

    @Override
    public List<UserModel> getListFollowing(int userId) throws ExceptionHandler {
        List<UserModel> followingList = new ArrayList<>();
        for(UserRelation r:usersRelations) {
            if(r.getFollower() == userId) {
                try {
                    followingList.add(this.getUserById(r.getFollowing()));
                } catch (InvalidUserException e) {
                    throw new InvalidUserException();
                }
            }
        }
        if(followingList.size() == 0) {
            throw new NoFollowingException();
        }
        return followingList;
    }

    @Override
    public List<UserModel> getListFollowers(int userId) throws ExceptionHandler {
        List<UserModel> followersList = new ArrayList<>();
        for(UserRelation r:usersRelations) {
            if(r.getFollowing() == userId){
                try {
                    followersList.add(this.getUserById(r.getFollower()));
                } catch (InvalidUserException e) {
                    throw new InvalidUserException();
                }
            }
        }
        if(followersList.size() == 0) {
            throw new NoFollowersException();
        }
        return followersList;
    }

    @Override
    public int getFollowersAmount(int userId) throws ExceptionHandler {
        int amount = 0;
        for(UserRelation r:usersRelations) {
            if(r.getFollowing() == userId) {
                amount++;
            }
        }
        if(amount == 0) {
            throw new NoFollowersException();
        }
        return amount;
    }

}