package com.example.desafiospring.repositories;

import com.example.desafiospring.dtos.general.UserInfo;
import com.example.desafiospring.dtos.response.UserDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements IUserRepository{

    private final Map<Integer, UserInfo> usersDatabase;
    private final String PATH_NAME_USERS = "src/main/resources/static/users.json";

    public UserRepositoryImpl() {
        this.usersDatabase = loadDatabase(this.PATH_NAME_USERS);
    }

    private Map<Integer, UserInfo> loadDatabase(String pathName) {
        File file = null;
        try {
            file = ResourceUtils.getFile(pathName);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mapObject(file);
    }


    private Map<Integer, UserInfo> mapObject(File file) {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<Map<Integer, UserInfo>> typeReference = new TypeReference<>() {};

        Map<Integer, UserInfo> userDtos = null;
        try {
            userDtos = mapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userDtos;

    }

    @Override
    public void updateUsersFile() {
        System.out.println("guardando: "+ this.usersDatabase);
        try {
            ObjectMapper mapper = new ObjectMapper();

            // convert map to JSON file
            mapper.writeValue(Paths.get(this.PATH_NAME_USERS).toFile(), this.usersDatabase);
            System.out.println("Archivo sobreescrito");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserInfo getUser(Integer userId) {
        return usersDatabase.get(userId);
    }


}
