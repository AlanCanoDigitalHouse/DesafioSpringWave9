package com.desafiospring.socialmeli.services;

import com.desafiospring.socialmeli.dtos.responses.SellerFollowersCountDTO;
import com.desafiospring.socialmeli.dtos.responses.SellerFollowersDTO;

import java.util.List;

public interface ISeller {

    List<SellerFollowersCountDTO> sellerFollowersCount(int userId);

    List<SellerFollowersDTO> sellerFollowers(int userId);


}
