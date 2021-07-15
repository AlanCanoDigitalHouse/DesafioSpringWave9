package com.mercado_libre.bootcamp.spring.desafio.dtos.response;

import com.mercado_libre.bootcamp.spring.desafio.models.Seller;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SellerInformationResponseDTO {
    private List<Seller> sellers;
}
