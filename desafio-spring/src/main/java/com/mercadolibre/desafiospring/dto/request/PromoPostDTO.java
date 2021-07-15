package com.mercadolibre.desafiospring.dto.request;

import lombok.*;

@Getter
@Setter
public class PromoPostDTO extends PostDTO {
    private Boolean hasPromo;
    private double discount;
}
