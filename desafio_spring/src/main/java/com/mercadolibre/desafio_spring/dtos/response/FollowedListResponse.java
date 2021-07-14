package com.mercadolibre.desafio_spring.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowedListResponse {
    int userId;
    String userName;
    List<UserEntitieResponse> followed;
}
