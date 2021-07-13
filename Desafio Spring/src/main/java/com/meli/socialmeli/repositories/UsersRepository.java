package com.meli.socialmeli.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.socialmeli.exceptions.UserDoesNotExistException;
import com.meli.socialmeli.models.Follow;
import com.meli.socialmeli.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository
public class UsersRepository implements IUsersRepository{
    private final List<User> usersDatabase;
    private final List<Follow> usersFollows;

    public UsersRepository(){
        this.usersDatabase = loadUsersDataBase();
        this.usersFollows = loadFollowDataBase();
    }

    public void addFollow(Follow f) throws UserDoesNotExistException {
        if(!userExist(f.getToFollowUserId())){
            throw new UserDoesNotExistException("to follow id " + f.getToFollowUserId());
        }
        if(!userExist(f.getFollowerUserId())){
            throw new UserDoesNotExistException("follower id " + f.getFollowerUserId());
        }
        usersFollows.add(f); // Verification is done in the Service layer
    }

    public boolean followExist(Follow f){
        Optional<Follow> follow = this.usersFollows.stream()
                .filter(fs -> fs.getToFollowUserId() == f.getToFollowUserId() &&
                            fs.getFollowerUserId() == f.getFollowerUserId())
                .findAny();
        return follow.isPresent();
    }

    public boolean userExist(int userId) {
        Optional<User> user = this.usersDatabase.stream()
                .filter(u -> u.getUserId() == userId)
                .findAny();
        return user.isPresent();
    }

    private List<Follow> loadFollowDataBase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:follows.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Follow>> typeRef = new TypeReference<>() {};
        List<Follow> follows = null;
        try {
            follows = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return follows;
    }

    private List<User> loadUsersDataBase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:users.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<User>> typeRef = new TypeReference<>() {};
        List<User> users = null;
        try {
            users = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
}
