package com.meli.desafiospring.DTOs.response;

import lombok.Data;
import lombok.Getter;

@Getter
public class PromoPostResponseDTO extends PostResponseDTO{

    private Boolean hasPromo;
    private Double discount;

    public PromoPostResponseDTO(Long id_post, String date, DetailResponseDTO detailResponseDTO, String category, Double price, Double discount) {
        super(id_post, date, detailResponseDTO, category, price);
        this.hasPromo = true;
        this.discount = discount;
    }

    @Override
    public Boolean hasPromo() { return true; }

}
