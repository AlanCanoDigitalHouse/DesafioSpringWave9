package com.example.desafio1springboot.repositories;

import com.example.desafio1springboot.dtos.UserClientDTO;
import com.example.desafio1springboot.dtos.UserDTO;
import com.example.desafio1springboot.dtos.UserSellerDTO;
import com.example.desafio1springboot.handlers.RepositoryHandler;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements IUserRepository{
    private List<UserClientDTO> userClientDB;
    private List<UserSellerDTO> userSellerDB;

    public UserRepositoryImpl() {
        this.userClientDB = RepositoryHandler.loadDatabaseUserClient();
        this.userSellerDB = RepositoryHandler.loadDatabaseUserSeller();
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
}
