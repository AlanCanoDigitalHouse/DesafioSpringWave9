package com.mercadolibre.desafio1.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mercadolibre.desafio1.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserFollowListDTO {
    private Integer userId;
    private String userName;
    private ArrayList<UserDTO> followers;
    private ArrayList<UserDTO> followed;


    public UserFollowListDTO(Integer userId, String userName, ArrayList<UserDTO> followers, ArrayList<UserDTO> followed) {
        this.userId = userId;
        this.userName = userName;
        this.followers = followers;
        this.followed = followed;
    }
}
