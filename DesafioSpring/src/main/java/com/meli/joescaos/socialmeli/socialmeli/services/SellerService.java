package com.meli.joescaos.socialmeli.socialmeli.services;

import com.meli.joescaos.socialmeli.socialmeli.dtos.responses.SellerFollowersCountDto;
import com.meli.joescaos.socialmeli.socialmeli.dtos.responses.SellerFollowersDto;

public interface SellerService {
    SellerFollowersCountDto getFollowersCount(int userId);

    SellerFollowersDto getFollowersList(int userId, String order);

}
