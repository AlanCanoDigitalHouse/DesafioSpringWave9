package com.meli.joescaos.socialmeli.socialmeli.services;

import com.meli.joescaos.socialmeli.socialmeli.dtos.responses.BuyerFollowingsDto;
import com.meli.joescaos.socialmeli.socialmeli.dtos.responses.PostsListDto;
import com.meli.joescaos.socialmeli.socialmeli.exceptions.UserNotFoundException;

public interface BuyerService {
    void followSeller(int buyerId, int sellerId);

    void unfollowSeller(int buyerId, int sellerId);

    BuyerFollowingsDto getUserFollowedList(int userId, String order);

    PostsListDto getPostsList(int userId, String order);
}
