package com.meli.joescaos.socialmeli.socialmeli.repositories.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.joescaos.socialmeli.socialmeli.exceptions.UserNotFoundException;
import com.meli.joescaos.socialmeli.socialmeli.models.Buyer;
import com.meli.joescaos.socialmeli.socialmeli.repositories.BuyerRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class BuyerRepositoryImpl implements BuyerRepository {

    // Attributes
    public List<Buyer> buyers;

    // Constructor
    public BuyerRepositoryImpl() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            buyers = Arrays.asList(mapper.readValue(new ClassPathResource("static/buyers.json").getFile(), Buyer[].class));
        } catch (IOException e) {
            e.printStackTrace();
            buyers = new ArrayList<>();
        }
    }


    /**
     *
     * @param userId
     * @return Buyer
     */
    @Override
    public Buyer findById(int userId) {
        return buyers.stream().filter(buyerId -> buyerId.getUserId() == userId)
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException());
    }


}
