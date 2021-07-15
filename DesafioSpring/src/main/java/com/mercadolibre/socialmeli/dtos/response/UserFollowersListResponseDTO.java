package com.mercadolibre.socialmeli.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserFollowersListResponseDTO {
    private Integer userID;
    private String userName;
    private List<UserResponseDTO> followers;

}
