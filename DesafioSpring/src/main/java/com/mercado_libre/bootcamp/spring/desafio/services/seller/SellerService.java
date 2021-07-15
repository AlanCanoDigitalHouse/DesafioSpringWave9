package com.mercado_libre.bootcamp.spring.desafio.services.seller;

import com.mercado_libre.bootcamp.spring.desafio.dtos.response.FollowersCountResponseDTO;
import com.mercado_libre.bootcamp.spring.desafio.dtos.response.FollowersListResponseDTO;
import com.mercado_libre.bootcamp.spring.desafio.models.Post;
import com.mercado_libre.bootcamp.spring.desafio.models.Seller;

public interface SellerService {

    public Seller getSeller(int sellerId);

    public FollowersCountResponseDTO getFollowersCount(int userId);

    public FollowersListResponseDTO getFollowersList(int userId, String order);

    public void savePost(int userId, Post post);

}
