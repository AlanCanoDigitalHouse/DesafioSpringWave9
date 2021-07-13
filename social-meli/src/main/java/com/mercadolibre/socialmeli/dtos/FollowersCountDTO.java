package com.mercadolibre.socialmeli.dtos;

import lombok.Data;

@Data
public class FollowersCountDTO {

    private Integer userId;
    private String userName;
    private Long followers_count;

}
