package com.api.firstspringchallenge.repositories.seller.implementation;

import com.api.firstspringchallenge.models.Seller;
import com.api.firstspringchallenge.repositories.seller.SellerRepositoryI;
import com.api.firstspringchallenge.utils.FileUtils;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SellerRepository implements SellerRepositoryI {

    private List<Seller> sellers;

    @PostConstruct
    public void loadSellers() {
        Type listType = new TypeToken<List<Seller>>() {
        }.getType();
        String fileName = "static/json/sellers.json";
        try {
            this.sellers = FileUtils.getDataFrom(fileName, listType);
        } catch (IOException e) {
            this.sellers = new ArrayList<>();
        }
    }

    @Override
    public void addSeller(Seller user) {

    }

    @Override
    public void removeSeller(Seller user) {

    }

    @Override
    public boolean isSeller(int userId) {
        return this.sellers.stream().anyMatch(s -> s.getUserId() == userId);
    }

    @Override
    public Seller findSellerById(int userId) {
        return this.sellers.stream().filter(s -> s.getUserId() == userId).findAny().orElse(null);
    }

}
