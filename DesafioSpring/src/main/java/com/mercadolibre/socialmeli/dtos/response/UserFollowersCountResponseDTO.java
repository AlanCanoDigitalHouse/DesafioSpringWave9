package com.mercadolibre.socialmeli.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFollowersCountResponseDTO {
    private Integer userID;
    private String userName;
    private Integer followers_count;
}
