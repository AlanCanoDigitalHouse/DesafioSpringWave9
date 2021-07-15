package com.mercadolibre.desafio.demo.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FollowersCountResponseDTO {
    private final Integer userId;
    private final String userName;
    private final Integer followers_count;
}
