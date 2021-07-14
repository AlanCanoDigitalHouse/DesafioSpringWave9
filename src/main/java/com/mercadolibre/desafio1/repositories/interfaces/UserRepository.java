package com.mercadolibre.desafio1.repositories.interfaces;

import com.mercadolibre.desafio1.dto.UserDTO;

import java.util.ArrayList;
import java.util.Map;


public interface UserRepository {
    void initBdd(Map<Integer, UserDTO> usersNew);
    UserDTO saveUser(UserDTO user);
    UserDTO getUserById(Integer userId);
    ArrayList<Integer> getFollowsById(Integer userId);
}
