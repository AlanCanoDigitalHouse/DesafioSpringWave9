package com.socialmeli.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialmeli.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class SocialMeliRepositoryImp implements SocialMeliRepository {

    private final AtomicInteger postIndex = new AtomicInteger(0);
    private final AtomicInteger productIndex = new AtomicInteger(0);

    private static final String fileResource = "./src/main/resources/static/database.json";

    private Map<Integer, UserDTO> database = loadUsers();

    @Override
    public UserDTO save(UserDTO user) {
        if (user.getUserId() == null)
            user.setUserId(database.values().size());
        database.put(user.getUserId(), user);
        return user;
    }

    @Override
    public Optional<UserDTO> findUserByUserId(Integer userId) {

        UserDTO userDTO = database.get(userId);
        return Optional.ofNullable(userDTO);
    }

    @Override
    public Integer lastPostId() {
        return postIndex.addAndGet(1);
    }

    @Override
    public Integer lastProductId() {
        return productIndex.addAndGet(1);
    }

    //levanta los usuarios almacenados en database.json y los pasa a un HashMap.
    private static Map<Integer, UserDTO> loadUsers() {
        List<UserDTO> list = loadDatabase();
        Map<Integer, UserDTO> userDTOMap = new HashMap<>();
        if(Objects.isNull(list)) {
            return userDTOMap;
        }
        for(UserDTO u : list) {
            userDTOMap.put(u.getUserId(), u);
        }
        return userDTOMap;
    }

    private static List<UserDTO> loadDatabase(){
        File file = null;
        try{
            file = ResourceUtils.getFile(fileResource);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return mapObject(file);
    }

    private static List<UserDTO> mapObject(File file){
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<UserDTO>> typeReference = new TypeReference<>(){};
        List<UserDTO> userDTOS = null;
        try {
            userDTOS = objectMapper.readValue(file, typeReference);
        }catch (IOException e){
            e.printStackTrace();
        }

        return userDTOS;
    }
}
