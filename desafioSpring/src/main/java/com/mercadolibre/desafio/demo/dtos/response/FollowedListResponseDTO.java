package com.mercadolibre.desafio.demo.dtos.response;

import com.mercadolibre.desafio.demo.models.SellerModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FollowedListResponseDTO {
    private final Integer userId;
    private final String userName;
    private final List<SellerModel> followed;
}
