package com.mercadolibre.socialmeli.dto.response;

import lombok.Data;

@Data
public class FollowersCountResponseDTO {
    private Integer userId;
    private String userName;
    private Integer followers_count;
}
