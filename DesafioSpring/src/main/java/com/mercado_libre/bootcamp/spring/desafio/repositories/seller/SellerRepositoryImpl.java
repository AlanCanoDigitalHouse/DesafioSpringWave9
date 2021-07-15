package com.mercado_libre.bootcamp.spring.desafio.repositories.seller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercado_libre.bootcamp.spring.desafio.exceptions.UserRepositoryException;
import com.mercado_libre.bootcamp.spring.desafio.models.Post;
import com.mercado_libre.bootcamp.spring.desafio.models.Seller;
import lombok.Getter;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Repository
public class SellerRepositoryImpl implements SellerRepository {

    private List<Seller> sellers;

    @PostConstruct
    public void loadDatabase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/sellers.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sellers = mapObject(file);
    }

    private List<Seller> mapObject(File file) {
        var objectMapper = new ObjectMapper();
        TypeReference<List<Seller>> typeReference = new TypeReference<>() {
        };

        try {
            return objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Seller getSeller(int userId) {
        return sellers.stream().filter(x -> x.getUserId() == userId)
                .findFirst()
                .orElseThrow(() -> new UserRepositoryException("Inválido número de vendedor ingresado"));
    }

    @Override
    public void savePost(int userId, Post post) {
        getSeller(userId).getPosts().add(post);
    }
}
