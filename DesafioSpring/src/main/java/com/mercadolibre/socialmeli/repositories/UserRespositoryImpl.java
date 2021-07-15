package com.mercadolibre.socialmeli.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.socialmeli.exceptions.ExceptionUserNotFound;
import com.mercadolibre.socialmeli.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserRespositoryImpl implements UserRepository {

    @Override
    public User findUserById(Integer userId) throws ExceptionUserNotFound {
        User result = null;
        List<User> users = loadDatabase();
        if (Objects.nonNull(users)) {
            Optional<User> item = users.stream().filter(user -> user.getUserId().equals(userId)).findFirst();
            if (item.isPresent()) {
                result = item.get();
            } else {
                throw new ExceptionUserNotFound("El userId " + userId + " no existe");
            }
        }
        return result;
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = loadDatabase();
        return users;
    }

    @Override
    public void save(User u) {
        ObjectMapper objectMapper = new ObjectMapper();
        User result = null;
        List<User> users = loadDatabase();
        if (Objects.nonNull(users)) {
            Optional<User> item = users.stream().filter(user -> user.getUserId().equals(u.getUserId())).findFirst();
            if (item.isPresent()) {
                result = item.get();
                result.setFollowers(u.getFollowers());
                result.setFollowed(u.getFollowed());
            }
            try {
                objectMapper.writeValue(new File("src/main/resources/static/users.json"), users);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private List<User> loadDatabase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("src/main/resources/static/users.json");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return mapObject(file);
    }

    private List<User> mapObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<User>> typeReference = new TypeReference<>() {
        };
        List<User> users = new ArrayList<>();
        try {
            users = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }
}
