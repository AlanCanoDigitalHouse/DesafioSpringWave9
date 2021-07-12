package com.desafiospring.socialmeli.services;

import com.desafiospring.socialmeli.dtos.responses.BuyerFollowedDTO;
import com.desafiospring.socialmeli.repositories.BuyerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerService implements IBuyer {

    BuyerRepository repository;

    public BuyerService(BuyerRepository repository){
        this.repository = repository;
    }


    @Override
    public void followSeller(int userId, int userIdToFollow) {

    }

    @Override
    public List<BuyerFollowedDTO> buyerFollowed(int userId) {
        return null;
    }
}
