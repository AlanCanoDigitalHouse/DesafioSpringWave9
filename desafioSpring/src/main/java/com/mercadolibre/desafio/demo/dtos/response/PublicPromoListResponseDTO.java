package com.mercadolibre.desafio.demo.dtos.response;

import com.mercadolibre.desafio.demo.models.PublicPromoModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PublicPromoListResponseDTO {
    private Integer userId;
    private String userName;
    private List<PublicPromoModel> posts;
}
