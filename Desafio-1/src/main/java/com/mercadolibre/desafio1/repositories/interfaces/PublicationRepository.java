package com.mercadolibre.desafio1.repositories.interfaces;

import com.mercadolibre.desafio1.dto.response.ProductResponseDTO;
import com.mercadolibre.desafio1.dto.response.PublicationResponseDTO;

import java.time.LocalDate;
import java.util.ArrayList;

public interface PublicationRepository {
    PublicationResponseDTO getPublicationById(Integer productId);
    PublicationResponseDTO addPublication(Integer userId, LocalDate date, ProductResponseDTO detail, Integer category, Double price, Boolean hasPromo, Double discount);
    ArrayList<PublicationResponseDTO> getPublicationsByUserId(Integer id, Boolean promo);

}