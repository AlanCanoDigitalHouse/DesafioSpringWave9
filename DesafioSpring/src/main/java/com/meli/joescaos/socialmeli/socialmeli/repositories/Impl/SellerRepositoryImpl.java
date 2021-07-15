package com.meli.joescaos.socialmeli.socialmeli.repositories.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.joescaos.socialmeli.socialmeli.exceptions.UserNotFoundException;
import com.meli.joescaos.socialmeli.socialmeli.models.Seller;
import com.meli.joescaos.socialmeli.socialmeli.repositories.SellerRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Repository
public class SellerRepositoryImpl implements SellerRepository {

    // Attributes
    public List<Seller> sellers;

    /*
     * Constructor
     */
    public SellerRepositoryImpl(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            sellers = Arrays.asList(mapper.readValue(new ClassPathResource("static/sellers.json").getFile(), Seller[].class));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param userId
     * @return Seller
     */
    @Override
    public Seller findById(int userId) {
        return sellers.stream().filter(seller -> seller.getUserId() == userId)
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException());
    }

}
