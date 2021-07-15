package com.example.socialmeli.repositories;

import com.example.socialmeli.dtos.UserDto;
import com.example.socialmeli.exceptions.NotFoundException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Repository
public class UserRepository implements IUserRepository{
    private final List<UserDto> users;

    public UserRepository() {
        this.users = getDatabase();
    }

    @Override
    public List<UserDto> getUsers() {
        return this.users;
    }

    @Override
    public UserDto findUser(Integer id){
        UserDto user = null;

        if(Objects.nonNull(this.users)){
            Optional<UserDto> item = this.users.stream().filter(userDto -> userDto.getUserId().equals(id)).findFirst();
            if (item.isPresent())
                user = item.get();
            else
                throw new NotFoundException("Usuario con id: " + id + " no encontrado");
        }
        return user;
    }


    private List<UserDto> getDatabase(){
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:static/users.json");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return mapObject(file);
    }

    @Override
    public void saveUsers(){
        try {
            var file = ResourceUtils.getFile("classpath:static/users.json");
            var mapper = new ObjectMapper();
            mapper.writeValue(file, this.users);
        } catch (IOException exception) {
            exception.printStackTrace();
            //throw new DatabaseException("There was an error while writing on the database");
        }
    }

    private List<UserDto> mapObject(File file){
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<UserDto>> typeReference = new TypeReference<>(){};
        List<UserDto> userDtos = null;
        try {
            userDtos = objectMapper.readValue(file, typeReference);
        }catch (IOException e){
            e.printStackTrace();
        }
        return userDtos;
    }
}
