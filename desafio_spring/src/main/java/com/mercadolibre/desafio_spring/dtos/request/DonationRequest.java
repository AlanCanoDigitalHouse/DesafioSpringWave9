package com.mercadolibre.desafio_spring.dtos.request;

import com.mercadolibre.desafio_spring.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationRequest {
    Integer donor;
    Double donation;
    Integer post_id;
}
