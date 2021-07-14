package com.mercadolibre.desafio_spring.dtos.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowersListResponse {
    int userId;
    String userName;
    ArrayList<UserEntitieResponse> followers;
}
