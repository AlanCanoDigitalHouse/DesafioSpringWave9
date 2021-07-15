package com.mercadolibre.desafio.spring.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.desafio.spring.dtos.request.PostDto;
import com.mercadolibre.desafio.spring.dtos.request.UserDto;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class AppSocialMeliRepository implements IRepository {

    @Override
    public UserDto findUserById(Integer id) {
        List<UserDto> users;
        users = loadUsersDatabase();
        UserDto result = null;

        if (Objects.nonNull(users)) {
            Optional<UserDto> item = users.stream().filter(UserDto -> UserDto.getUserId().equals(id)).findFirst();
            if (item.isPresent()) {
                result = item.get();
            }
        }
        return result;
    }


    /**
     * Load into the repository the data users entered in the json file
     */

    @Override
    public List<UserDto> loadUsersDatabase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("src/main/resources/users.json");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<UserDto>> typeReference = new TypeReference<>() {
        };
        List<UserDto> user = null;
        try {
            user = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }


    /**
     * Load into the repository the posts entered in the json file
     */

    @Override
    public List<PostDto> loadPostsDatabase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("src/main/resources/posts.json");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<PostDto>> typeReference = new TypeReference<>() {
        };
        List<PostDto> post = null;
        try {
            post = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return post;
    }


    /**
     * Save the users Id in json file
     */
    @Override
    public void writeDataBase(String path, List<?> list) {
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        try {
            objectMapper.writeValue(new File(path), list);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("The specified file cannot be found: " + path);
        }
    }

}
