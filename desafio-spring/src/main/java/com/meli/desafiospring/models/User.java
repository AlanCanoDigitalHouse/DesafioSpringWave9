package com.meli.desafiospring.models;

import com.meli.desafiospring.DTOs.PostDTO;

import java.util.List;

public class User {

    String Long;
    String userName;
    List<PostDTO> postDTOS;
    List<User> followers;
    List<User> followed;

}
