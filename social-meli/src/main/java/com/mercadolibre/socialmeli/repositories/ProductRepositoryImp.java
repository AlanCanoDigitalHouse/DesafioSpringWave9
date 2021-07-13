package com.mercadolibre.socialmeli.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.socialmeli.dto.Publication;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImp implements ProductRepository {
    private List<Publication> database;

    public ProductRepositoryImp() {
        database = loadDataBase();
    }


    @Override
    public List<Publication> findAllByUser(int userId) {
        List<Publication> response = database.stream().filter(x -> x.getUserId() == userId).collect(Collectors.toList());
        return response;
    }


    @Override
    public Publication addPublication(Publication publication) {
        database.add(database.size(), publication);
        return publication;
    }

    @Override
    public List<Publication> findAllPromoByUser(int userId) {
        List<Publication> response = database.stream().
                filter(x -> x.getUserId() == userId && x.isHasPromo()).
                collect(Collectors.toList());
        return response;
    }

    @Override
    public Publication addProductPromotion(Publication promo) {
        database.add(database.size(), promo);
        return promo;
    }

    @Override
    public List<Publication> findAll() {
        return database;
    }

    private List<Publication> loadDataBase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/podst.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mapObjectR(file);
    }

    private List<Publication> mapObjectR(File file) {
        ObjectMapper obj = new ObjectMapper();
        TypeReference<List<Publication>> typeRef = new TypeReference<List<Publication>>() {
        };
        List<Publication> ingreDTO = null;
        try {
            ingreDTO = obj.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ingreDTO;
    }

}
