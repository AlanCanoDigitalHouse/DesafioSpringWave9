package com.desafiospring.socialmeli.services;

import com.desafiospring.socialmeli.dtos.responses.BuyerFollowedDTO;

import java.util.List;

public interface IBuyer {

    void followSeller(int userId, int userIdToFollow);

    List<BuyerFollowedDTO> buyerFollowed(int userId);



}
