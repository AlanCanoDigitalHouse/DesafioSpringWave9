package com.mercadolibre.desafio_spring.dtos.request;

import com.mercadolibre.desafio_spring.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromoPostRequest extends Post {
    boolean hasPromo;
    double discount;
}
