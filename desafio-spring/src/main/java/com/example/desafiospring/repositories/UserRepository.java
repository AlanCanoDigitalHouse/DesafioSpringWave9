package com.example.desafiospring.repositories;

import com.example.desafiospring.exceptions.UserException;
import com.example.desafiospring.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepository implements IUserRepository {

    private List<User> loadDB() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/users.json");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return mapObject(file);
    }

    private List<User> mapObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<User>> typeReference = new TypeReference<>() {
        };
        List<User> users = null;
        try {
            users = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    private List<User> saveToFile(List<User> users) {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/users.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String listJson = objectMapper.writeValueAsString(users);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(listJson);
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findById(Long id) throws UserException {
        List<User> users = this.loadDB();
        Optional<User> result = users.stream().filter(u -> u.getUserId().equals(id)).findAny();
        if (result.isPresent())
            return result.get();
        else
            throw new UserException(UserException.USER_NOT_EXISTS + id);
    }

    @Override
    public Collection<User> findByIds(Collection<Long> ids) {
        List<User> users = this.loadDB();
        return users.stream().filter(u -> ids.contains(u.getUserId())).collect(Collectors.toList());
    }
}
