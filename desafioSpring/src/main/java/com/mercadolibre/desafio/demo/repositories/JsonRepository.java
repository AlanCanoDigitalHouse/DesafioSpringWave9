package com.mercadolibre.desafio.demo.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.desafio.demo.models.UserModel;
import com.mercadolibre.desafio.demo.services.ProductService;
import com.mercadolibre.desafio.demo.services.UserService;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JsonRepository {
    // Injection of dependencies
    ProductService productService;
    UserService userService;

    public JsonRepository(DataBaseUserRepository dataBaseUserRepository, DataBaseProductsRepository dataBaseProductsRepository,
                          ProductService productService,
                          UserService userService) {
        // load user.json file and create a UserModel List
        List<UserModel> usersData = this.mapDataBase("src/main/resources/static/users.json");

        // load data ========================
        userService.loadDataBase(usersData);
        userService.loadDataInList();
        productService.loadDataInList(usersData);
        // end load data ========================
    }

    // read user.json file
    private List<UserModel> mapDataBase(String path) {
        ObjectMapper objectMapper = new ObjectMapper(); // indagar en este mappeador
        TypeReference<List<UserModel>> typeReference = new TypeReference<>() {};
        List<UserModel> objects = null;
        try{
            objects = objectMapper.readValue(new File(path),
                    objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, UserModel.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objects;
    }
}
