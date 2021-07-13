package com.example.desafiospring.repository.implementations;

import com.example.desafiospring.entities.UserEntity;
import com.example.desafiospring.exceptions.general.DBNotAvailableException;
import com.example.desafiospring.exceptions.general.NoSuchElementInDBException;
import com.example.desafiospring.repository.interfaces.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserRepository {

    public static final java.lang.String USERS_DB_ROUTE = "classpath:static/users.json";

    @Override
    public void validateExistOrException(Integer id) {
        if (getDatabaseUsers().stream().noneMatch(u -> u.getUserId().equals(id)))
        {
            throw new NoSuchElementInDBException("No user with ID:" + id);
        }
    }

    @Override
    public UserEntity getUserByID(Integer userId) {
        UserEntity user;
        Optional<UserEntity> userEntityOptional = getDatabaseUsers().stream().filter(u -> u.getUserId().equals(userId)).findFirst();
        if (userEntityOptional.isPresent()){
            user = userEntityOptional.get();
        }else{
            throw new NoSuchElementInDBException("No user with ID:" + userId);
        }
        return user;
    }

    @Override
    public List<UserEntity> getUsersByID(List<Integer> followerIDS) {
        return getDatabaseUsers().stream().filter(u -> followerIDS.contains(u.getUserId())).collect(Collectors.toList());
    }

    private List<UserEntity> getDatabaseUsers() {
        File file;
        try {
            file = ResourceUtils.getFile(USERS_DB_ROUTE);
        } catch (Exception e) {
            throw new DBNotAvailableException("Error finding DB", e);
        }
        return loadUsersJSON(file);
    }

    private List<UserEntity> loadUsersJSON(File file) throws DBNotAvailableException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<UserEntity>> typeReference = new TypeReference<>() {
        };
        List<UserEntity> users;
        try {
            users = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            throw new DBNotAvailableException("Error reading DB", e);
        }
        return users;
    }
}
