package com.api.firstspringchallenge.dtos.response;

import com.api.firstspringchallenge.models.Seller;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PromoPostsCountResponseDTO {

    private int userId;
    private String username;
    private int promoProductsCount;

    public PromoPostsCountResponseDTO(Seller seller, int promoProductsCount) {
        this.userId = seller.getUserId();
        this.username = seller.getUsername();
        this.promoProductsCount = promoProductsCount;
    }
}