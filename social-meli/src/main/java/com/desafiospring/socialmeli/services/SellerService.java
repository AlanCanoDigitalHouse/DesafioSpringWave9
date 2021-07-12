package com.desafiospring.socialmeli.services;

import com.desafiospring.socialmeli.dtos.responses.SellerFollowersCountDTO;
import com.desafiospring.socialmeli.dtos.responses.SellerFollowersDTO;
import com.desafiospring.socialmeli.repositories.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService implements ISeller {

    SellerRepository repository;

    public SellerService(SellerRepository repository){
        this.repository = repository;
    }

    @Override
    public List<SellerFollowersCountDTO> sellerFollowersCount(int userId) {
        return null;
    }

    @Override
    public List<SellerFollowersDTO> sellerFollowers(int userId) {
        return null;
    }
}
