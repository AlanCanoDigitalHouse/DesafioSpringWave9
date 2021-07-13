package com.mercadolibre.socialmeli.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.socialmeli.dto.User;
import com.mercadolibre.socialmeli.dto.UserToUser;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImp implements UserRepository {
    private List<User> database;
    private List<UserToUser> databaseR;

    public UserRepositoryImp() {
        database = loadDataBase();
        databaseR = loadDataBaseR();
    }


    @Override
    public List<User> findAllUser() {
        return database;
    }


    @Override
    public User findUserById(int user) {
        Optional<User> response = database.stream().filter(u -> u.getUserId() == user).findFirst();
        return response.get();
    }

    @Override
    public List<User> findFollowedByUser(int user) {
        List<UserToUser> relations = findAllRelationByUserFollowed(user);
        List<User> response = new ArrayList<>();
        for (UserToUser u : relations) {
            Optional<User>  res = database.stream().filter(a -> a.getUserId() == u.getUserIdToFollow()).findFirst();
            response.add(res.get());
        }
        return response;
    }

    @Override
    public List<User> findFollowersByUser(int user) {
        List<User> response = new ArrayList<>();
        for (UserToUser u : findAllRelationByUserFollowers(user)) {
            Optional<User>  res = database.stream().filter(a -> a.getUserId() == u.getUserId()).findFirst();
            response.add(res.get());
        }
        return response;
    }

    @Override
    public List<UserToUser> findAllRelationByUserFollowed(int user) {
        List<UserToUser> response = databaseR.stream().filter(x -> x.getUserId() == user).collect(Collectors.toList());
        return response;
    }

    @Override
    public List<UserToUser> findAllRelationByUserFollowers(int user) {
        List<UserToUser> response = databaseR.stream().filter(x -> x.getUserIdToFollow() == user).collect(Collectors.toList());
        return response;
    }

    @Override
    public void deleteRelation(int userid, int userIdToFollow) {
        databaseR.remove(new UserToUser(userid, userIdToFollow));
    }



    @Override
    public void addFollowUser(int user, int userF) {
        databaseR.add(databaseR.size(), new UserToUser(user, userF));
    }

    private List<User> loadDataBase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/users.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mapObject(file);
    }

    private List<User> mapObject(File file) {
        ObjectMapper obj = new ObjectMapper();
        TypeReference<List<User>> typeRef = new TypeReference<List<User>>() {
        };
        List<User> ingreDTO = null;
        try {
            ingreDTO = obj.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ingreDTO;
    }

    private List<UserToUser> loadDataBaseR() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/UserToUser.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mapObjectR(file);
    }

    private List<UserToUser> mapObjectR(File file) {
        ObjectMapper obj = new ObjectMapper();
        TypeReference<List<UserToUser>> typeRef = new TypeReference<List<UserToUser>>() {
        };
        List<UserToUser> ingreDTO = null;
        try {
            ingreDTO = obj.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ingreDTO;
    }


    //
    //
    //
    //            result.response = database.stream().filter(a -> a.getUserId() == x.getUserIdToFollow()).collect(Collectors.toList());
    //        });
}
