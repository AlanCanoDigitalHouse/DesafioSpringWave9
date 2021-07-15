package com.mercadolibre.socialmeli.dto.request;

import com.mercadolibre.socialmeli.entity.Post;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

public class PostPromoRequestDTO extends Post {

    @NotNull
    @Override
    public Double getDiscount() {
        return super.getDiscount();
    }

    @NotNull
    @AssertTrue(message = "HasPromo field must be true")
    @Override
    public Boolean getHasPromo() {
        return super.getHasPromo();
    }
}
