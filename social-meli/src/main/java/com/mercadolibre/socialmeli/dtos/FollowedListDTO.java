package com.mercadolibre.socialmeli.dtos;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class FollowedListDTO {

    private Integer userId;
    private String userName;
    private List<UserDTO> followed = new LinkedList<>();

}
