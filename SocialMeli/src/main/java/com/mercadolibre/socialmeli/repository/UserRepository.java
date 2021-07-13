package com.mercadolibre.socialmeli.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.socialmeli.entity.User;
import com.mercadolibre.socialmeli.exception.UserNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final List<User> usersDatabase = (this.loadDatabase()).orElse(new ArrayList<>());

    public User findUserById(int id){
        Optional<User> user = usersDatabase.stream().filter(
                (item) -> item.getUserId() == id)
                .findFirst();
        if (user.isEmpty()) throw new UserNotFoundException();

        return user.get();
    }

    public Optional<List<User>> loadDatabase(){
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:static/usersData.json");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return mapObject(file);
    }

    private Optional<List<User>> mapObject(File file){
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<User>> typeReference = new TypeReference<>(){};
        Optional<List<User>> usersData = Optional.ofNullable(null);
        try {
            usersData = Optional.ofNullable(objectMapper.readValue(file, typeReference));
        }catch (Exception e){
            e.printStackTrace();
        }
        return usersData;
    }
}
