package com.mercadolibre.social_meli.entity;

import com.mercadolibre.social_meli.dto.response.UserResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class User {

    private String userName;
    private Integer userId;
    private List<UserResponseDTO> followers;
    private List<UserResponseDTO> followed;

}
