package com.mercadolibre.socialmeli.dtos;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class FollowersListDTO {

    private Integer userId;
    private String userName;
    private List<UserDTO> followers = new LinkedList<>();

}
