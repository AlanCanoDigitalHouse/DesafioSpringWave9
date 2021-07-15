package com.mercadolibre.socialmeli.repositories.imp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.socialmeli.dto.UserToUser;
import com.mercadolibre.socialmeli.repositories.UserToUserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
@Repository
public class UserToUserRepositoryImp implements UserToUserRepository {

    private List<UserToUser> databaseR;

    public UserToUserRepositoryImp() { databaseR = loadDataBase(); }

    @Override
    public List<UserToUser> findAllRelationByUserFollowed(int user) {
        List<UserToUser> response = databaseR.stream().filter(x -> x.getUserId() == user).collect(Collectors.toList());
        return response;
    }

    @Override
    public List<UserToUser> findAllRelationByUserFollowers(int user) {
        List<UserToUser> response = databaseR.stream().filter(x -> x.getUserIdToFollow() == user).collect(Collectors.toList());
        return response;
    }

    @Override
    public void deleteRelation(int userid, int userIdToFollow) {
        databaseR.remove(new UserToUser(userid, userIdToFollow));
    }

    @Override
    public boolean relationExist(int user, int userF) {
        return databaseR.contains(new UserToUser(user, userF));
    }


    @Override
    public void addFollowUser(int user, int userF) {
        databaseR.add(databaseR.size(), new UserToUser(user, userF));
    }

    private List<UserToUser> loadDataBase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/UserToUser.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mapObjectR(file);
    }

    private List<UserToUser> mapObjectR(File file) {
        ObjectMapper obj = new ObjectMapper();
        TypeReference<List<UserToUser>> typeRef = new TypeReference<List<UserToUser>>() {
        };
        List<UserToUser> ingreDTO = null;
        try {
            ingreDTO = obj.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ingreDTO;
    }
}
