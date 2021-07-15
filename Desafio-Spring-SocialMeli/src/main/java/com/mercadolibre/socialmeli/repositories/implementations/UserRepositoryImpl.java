package com.mercadolibre.socialmeli.repositories.implementations;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.socialmeli.dtos.users.UserFollowsRespDTO;
import com.mercadolibre.socialmeli.repositories.interfaces.IUserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository("userRepo")
public class UserRepositoryImpl implements IUserRepository {

    private static List<UserFollowsRespDTO> userList;

    public UserRepositoryImpl() {
        userList = loadDatabase();
    }

    @Override
    public UserFollowsRespDTO find(Integer id) {
        UserFollowsRespDTO userFinded;
        Optional<UserFollowsRespDTO> userOptional =
                userList
                        .stream()
                        .filter(user -> user.getUserId().equals(id))
                        .findFirst();
        userFinded = userOptional.orElse(null);
        return userFinded;
    }


    private List<UserFollowsRespDTO> loadDatabase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/users.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return mapToObject(file);
    }

    private List<UserFollowsRespDTO> mapToObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<UserFollowsRespDTO>> typeReference = new TypeReference<>() {
        };
        List<UserFollowsRespDTO> usersBase = null;
        try {
            usersBase = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usersBase;
    }
}
