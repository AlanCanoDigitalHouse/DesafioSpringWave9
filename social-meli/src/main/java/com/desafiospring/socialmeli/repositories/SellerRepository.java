package com.desafiospring.socialmeli.repositories;

import com.desafiospring.socialmeli.models.Buyer;
import com.desafiospring.socialmeli.models.Seller;
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
public class SellerRepository implements IRepository<Seller> {

    private List<Seller> sellers;

    public SellerRepository() {
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:static/sellers.json");

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        this.sellers = mapObject(file);
    }


    public List<Seller> mapObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Seller>> typeReference = new TypeReference<>(){};
        List<Seller> sellers = null;
        try {
            sellers = objectMapper.readValue(file, typeReference);
        }catch (IOException e){
            e.printStackTrace();
        }

        return sellers;
    }

    @Override
    public Seller add(Seller item) {
        return null;
    }

    @Override
    public Seller get(int itemId) {
        Optional<Seller> seller = this.sellers.stream()
                .filter(s -> s.getUserId() == itemId).findFirst();
        return seller.orElse(null);
    }

    @Override
    public Seller update(Seller item) {
        return null;
    }

    @Override
    public void delete(int itemId) {

    }

}
