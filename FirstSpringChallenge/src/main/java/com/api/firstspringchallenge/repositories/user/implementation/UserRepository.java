package com.api.firstspringchallenge.repositories.user.implementation;

import com.api.firstspringchallenge.models.Seller;
import com.api.firstspringchallenge.models.User;
import com.api.firstspringchallenge.repositories.user.SellerRepositoryI;
import com.api.firstspringchallenge.utils.FileUtils;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements SellerRepositoryI {

    private List<User> users;

    @PostConstruct
    public void loadSellers() {
        Type listType = new TypeToken<List<Seller>>() {
        }.getType();
        String fileName = "static/json/sellers.json";
        try {
            this.users = FileUtils.getDataFrom(fileName, listType);
        } catch (IOException e) {
            this.users = new ArrayList<>();
        }
    }

    @Override
    public boolean isSeller(int userId) {
        return this.users.stream().anyMatch(s -> s.getUserId() == userId);
    }

    @Override
    public User findSellerById(int userId) {
        return this.users.stream().filter(s -> s.getUserId() == userId).findAny().orElse(null);
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

}
