package com.meli.desafiospring.DTOs.request;

import com.meli.desafiospring.DTOs.response.DetailResponseDTO;
import com.meli.desafiospring.DTOs.response.PostResponseDTO;
import com.meli.desafiospring.DTOs.response.PromoPostResponseDTO;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Positive;

@Data
@Validated
public class PromoPostRequestDTO extends PostRequestDTO{

    @AssertTrue
    private Boolean hasPromo;
    @Positive
    @DecimalMax( value = "1")
    private Double discount;

    @Override
    public PostResponseDTO toPostResponseDTO(Long id_post, Long detailProductId) {
        DetailResponseDTO detailResponseDTO = new DetailResponseDTO(this.detail, detailProductId);
        return new PromoPostResponseDTO( id_post, this.date, detailResponseDTO, this.category, this.price, this.discount);
    }

}
