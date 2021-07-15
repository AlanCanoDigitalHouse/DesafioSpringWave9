package com.mercado_libre.bootcamp.spring.desafio.repositories.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercado_libre.bootcamp.spring.desafio.exceptions.UserRepositoryException;
import com.mercado_libre.bootcamp.spring.desafio.models.User;
import lombok.Getter;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Getter
    private List<User> users;

    @PostConstruct
    public void loadDatabase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/users.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            users = new ArrayList<>();
        }
        users = mapObject(file);
    }

    private List<User> mapObject(File file) {
        var objectMapper = new ObjectMapper();
        TypeReference<List<User>> typeReference = new TypeReference<>() {
        };

        try {
            return objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public User getUser(int userId) {
        return users.stream().filter(x -> x.getUserId() == userId)
                .findFirst()
                .orElseThrow(() -> new UserRepositoryException("Inválido número de comprador ingresado"));
    }
}
