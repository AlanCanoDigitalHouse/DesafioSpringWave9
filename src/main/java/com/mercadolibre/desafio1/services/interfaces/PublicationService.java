package com.mercadolibre.desafio1.services.interfaces;

import com.mercadolibre.desafio1.dto.request.PublicationRequestDTO;
import com.mercadolibre.desafio1.dto.response.FollowedUserListResponseDTO;
import com.mercadolibre.desafio1.dto.response.PublicationResponseDTO;
import com.mercadolibre.desafio1.dto.response.UserPromoCountResponseDTO;
import com.mercadolibre.desafio1.dto.response.UserPromoListResponseDTO;
import com.mercadolibre.desafio1.exceptions.DateAfterNowException;
import com.mercadolibre.desafio1.exceptions.UserNotExistException;

public interface PublicationService {
    PublicationResponseDTO newPost(PublicationRequestDTO publicationRequestDTO,Boolean isPromo) throws UserNotExistException, DateAfterNowException;
    FollowedUserListResponseDTO getFollowersUserList(Integer userId,String order) throws UserNotExistException;
    UserPromoListResponseDTO getListProductsPromoByUser(Integer userId) throws UserNotExistException;
    UserPromoCountResponseDTO getCountProductsPromoByUser(Integer userId) throws UserNotExistException;
}
