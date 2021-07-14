package com.mercadolibre.desafio.model;

import com.mercadolibre.desafio.dtos.requests.RequestDetailDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PostDiscount extends Post {

    private boolean hasPromo;
    private Double discount;

    public PostDiscount(Integer id_post, Date date, RequestDetailDto detail, Integer category, Double price, boolean hasPromo, Double discount) {
        super(id_post, date, detail, category, price);
        this.hasPromo = hasPromo;
        this.discount = discount;
    }
}
