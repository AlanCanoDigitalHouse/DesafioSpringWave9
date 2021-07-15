package com.meli.desafiospring.DTOs.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PromoPostCountResponseDTO {

    Integer userId;
    String userName;
    Integer promoproducts_count;

}
