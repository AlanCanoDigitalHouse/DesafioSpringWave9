package com.mercadolibre.socialmeli.services;

import com.mercadolibre.socialmeli.dto.Publication;
import com.mercadolibre.socialmeli.dto.respons.PromProductsResponse;
import com.mercadolibre.socialmeli.dto.respons.PromotionCountResponse;
import com.mercadolibre.socialmeli.dto.respons.PublicationsResponse;
import com.mercadolibre.socialmeli.exceptions.PreconditionError;
import com.mercadolibre.socialmeli.exceptions.UserBadRequest;

import java.text.ParseException;
import java.util.List;

public interface ProductService {

    Publication postPublications(Publication publication) throws UserBadRequest;

    PublicationsResponse getPublicationByFollowed(int userId, String order) throws ParseException, UserBadRequest;

    Publication postPromotion(Publication promotion) throws UserBadRequest, PreconditionError;

    PromotionCountResponse getCountProm(int userId) throws UserBadRequest;

    PromProductsResponse getProductsByProm(int userId,String order) throws UserBadRequest;

    List<Publication> getAll();
}
