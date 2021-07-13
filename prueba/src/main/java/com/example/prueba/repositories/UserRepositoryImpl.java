package com.example.prueba.repositories;

import com.example.prueba.dtos.UserClientDTO;
import com.example.prueba.dtos.UserDTO;
import com.example.prueba.dtos.UserSellerDTO;
import com.example.prueba.handlers.RepositoryHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements IUserRepository{
    private List<UserClientDTO> userClientDB;
    private List<UserSellerDTO> userSellerDB;

    public UserRepositoryImpl() {
        this.userClientDB = RepositoryHandler.loadDatabaseUserClient();
        this.userSellerDB = RepositoryHandler.loadDatabaseUserSeller();
        //this.userClientDB = RepositoryHandler.loadDatabase("userClient.json");
        //this.userSellerDB = RepositoryHandler.loadDatabase("userSeller.json");
    }

    @Override
    public UserDTO getUserSeller(Integer userId, Integer userIdToFollow) {
        UserDTO user = new UserDTO(userId);
        for(Integer i=0; i < userSellerDB.size(); i++) {
            if(userIdToFollow == userSellerDB.get(i).getUserId()) {
                var followers = userSellerDB.get(i).getFollowers();
                followers.add(user);
                userSellerDB.get(i).setFollowers(followers);
                return user;
            }
        }
        return null;
    }

    @Override
    public UserSellerDTO foundSeller(Integer userId) {
        Optional<UserSellerDTO> userSeller = userSellerDB.stream().filter(userSellerDTO -> userSellerDTO.getUserId() == userId).findFirst();
        UserSellerDTO result = null;
        if(userSeller.isPresent())
            return userSeller.get();
        return result;
    }
}