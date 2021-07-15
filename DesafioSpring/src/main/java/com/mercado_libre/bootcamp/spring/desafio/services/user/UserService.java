package com.mercado_libre.bootcamp.spring.desafio.services.user;

import com.mercado_libre.bootcamp.spring.desafio.dtos.response.FollowedListResponseDTO;
import com.mercado_libre.bootcamp.spring.desafio.dtos.response.FollowedProductResponseDTO;
import com.mercado_libre.bootcamp.spring.desafio.dtos.response.UserInformationResponseDTO;
import com.mercado_libre.bootcamp.spring.desafio.models.User;

public interface UserService {

    public User getUser(int userId);

    public FollowedListResponseDTO getFollowedList(int userId, String order);

    public FollowedProductResponseDTO getFollowedProducts(int userId, String order);

    public UserInformationResponseDTO getUsers();
}
