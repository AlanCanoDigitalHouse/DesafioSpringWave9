package com.desafiospring.socialmeli.repositories;

import com.desafiospring.socialmeli.dtos.models.Buyer;
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
public class BuyerRepository implements IRepository<Buyer>{

    private List<Buyer> buyers;

    public BuyerRepository(){
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:static/buyers.json");

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        this.buyers = mapObject(file);
    }

    public List<Buyer> mapObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Buyer>> typeReference = new TypeReference<>(){};
        List<Buyer> buyers = null;
        try {
            buyers = objectMapper.readValue(file, typeReference);
        }catch (IOException e){
            e.printStackTrace();
        }
        return buyers;
    }

    @Override
    public Buyer add(Buyer item) {
        return null;
    }

    @Override
    public Buyer get(int itemId) {
        Optional<Buyer> buyer = this.buyers.stream()
                .filter(b -> b.getUserId() == itemId).findFirst();
        return buyer.orElse(null);
    }

    @Override
    public Buyer update(Buyer item) {
        return null;
    }

    @Override
    public void delete(int itemId) {

    }


}
