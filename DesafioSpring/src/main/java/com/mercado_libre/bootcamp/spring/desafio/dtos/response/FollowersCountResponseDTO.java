package com.mercado_libre.bootcamp.spring.desafio.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FollowersCountResponseDTO {
    private int userId;
    private String userName;

    @JsonProperty("followers_count")
    private int followersCount;
}
