package com.socialmeli.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PromoPostResponseDTO extends PostResponseDTO {

    private Boolean hasPromo;
    private Double discount;

    public PromoPostResponseDTO(Integer idPost, String date, ProductResponseDTO detail, Integer category, Double price,
                                Boolean hasPromo, Double discount) {
        super(idPost, date, detail, category, price);

        this.hasPromo = hasPromo;
        this.discount = discount;
    }
}
