package com.example.desafiospring.repository;

import com.example.desafiospring.dtos.FollowDTO;
import com.example.desafiospring.dtos.request.UserRequestDTO;
import com.example.desafiospring.exceptions.UserNotFollowedException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class UserRepository implements IUserRepository {

    private static List<UserRequestDTO> database = new ArrayList<>();

    //El constructor carga del el repositorio de prueba
    UserRepository() {
        database = loadDB();
    }


    //metodos publicos
    @Override
    public boolean userExsistsDB(int pUserId) {
        return database.stream()
                .filter(user -> user.getUserId() == pUserId)
                .findFirst().isEmpty() ? false : true;
    }


    public List<UserRequestDTO> getUsersDB() {
        return database;
    }

    @Override
    public UserRequestDTO getUserDB(int pUserId) {

        return database.stream()
                .filter(user -> user.getUserId() == pUserId)
                .findFirst().orElse(null);
    }

    @Override
    public FollowDTO getUserFollowedDB(UserRequestDTO pUser, int pUserIdFollowed) throws UserNotFollowedException {

        FollowDTO vFollow = pUser.getUsersFollowed()
                .stream()
                .filter(followDTO -> followDTO.getUserId() == pUserIdFollowed)
                .findFirst().orElse(null);

        if (vFollow == null)
            throw new UserNotFollowedException("El userID: " + pUser.getUserId() + " no sigue al userID: " + pUserIdFollowed);

        return vFollow;
    }

    @Override
    public FollowDTO getUserFollowerDB(UserRequestDTO pUser, int pUserIdFollowed) {

        FollowDTO vFollow = pUser.getFollowers()
                .stream()
                .filter(followDTO -> followDTO.getUserId() == pUserIdFollowed)
                .findFirst().orElse(null);

        return vFollow;
    }


    //Metodos privados

    private List<UserRequestDTO> loadDB() {
        File file = null;

        try {
            file = ResourceUtils.getFile("classpath:static/database.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mapObject(file);
    }


    private List<UserRequestDTO> mapObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        TypeReference<List<UserRequestDTO>> typeReference = new TypeReference<>() {
        };
        List<UserRequestDTO> vUser = null;
        try {
            vUser = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vUser;
    }
}
