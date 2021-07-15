package com.example.desafio1springboot.dtos.responseDTO;

import com.example.desafio1springboot.dtos.PostInPromoDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PromoPostResponseDTO extends PostResponseDTO{
    private Boolean hasPromo;

    private Double discount;

    public static PromoPostResponseDTO toPromoPostDTO(PostInPromoDTO postInPromoDTO) {
        PromoPostResponseDTO postResponseDTO = new PromoPostResponseDTO();
        postResponseDTO.setId_post(postInPromoDTO.getId_post());
        postResponseDTO.setDate(postInPromoDTO.getDate());
        postResponseDTO.setDetail(postInPromoDTO.getDetail());
        postResponseDTO.setCategory(postInPromoDTO.getCategory());
        postResponseDTO.setPrice(postInPromoDTO.getPrice());
        postResponseDTO.setHasPromo(postInPromoDTO.getHasPromo());
        postResponseDTO.setDiscount(postInPromoDTO.getDiscount());
        return postResponseDTO;
    }
}
