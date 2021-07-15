package com.mercadolibre.socialmedia.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowersQuantityResponse {
    private Integer userId;
    private String userName;
    private Integer followers_count;
}
