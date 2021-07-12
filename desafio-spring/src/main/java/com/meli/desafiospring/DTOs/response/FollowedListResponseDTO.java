package com.meli.desafiospring.DTOs.response;

import java.util.List;

public class FollowedListResponseDTO {

    Integer userId;
    String userName;
    List<UserResponseDTO> followed;

}
