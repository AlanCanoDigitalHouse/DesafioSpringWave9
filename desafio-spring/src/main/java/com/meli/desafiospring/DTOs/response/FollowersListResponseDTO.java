package com.meli.desafiospring.DTOs.response;

import java.util.List;

public class FollowersListResponseDTO {

    Integer userId;
    String userName;
    List<UserResponseDTO> followers;

}
