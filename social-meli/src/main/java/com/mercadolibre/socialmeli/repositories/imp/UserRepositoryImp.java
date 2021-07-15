package com.mercadolibre.socialmeli.repositories.imp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.socialmeli.dto.User;
import com.mercadolibre.socialmeli.dto.UserToUser;
import com.mercadolibre.socialmeli.repositories.UserRepository;
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


    public UserRepositoryImp() {
        database = loadDataBase();
    }


    @Override
    public List<User> findAllUser() {
        return database;
    }


    @Override
    public User findUserById(int user) {
        User result = null;
        Optional<User> response = database.stream().filter(u -> u.getUserId() == user).findFirst();
        if (response.isPresent())
            result = response.get();
        return result;
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


}
