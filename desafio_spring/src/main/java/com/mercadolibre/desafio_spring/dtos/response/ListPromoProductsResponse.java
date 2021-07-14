package com.mercadolibre.desafio_spring.dtos.response;

import com.mercadolibre.desafio_spring.entities.PromoPost;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListPromoProductsResponse {
    int userId;
    String userName;
    ArrayList<PromoPost> posts;
}
