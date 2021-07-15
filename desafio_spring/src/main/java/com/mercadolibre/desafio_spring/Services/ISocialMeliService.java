package com.mercadolibre.desafio_spring.Services;

import com.mercadolibre.desafio_spring.dtos.request.DonationRequest;
import com.mercadolibre.desafio_spring.dtos.request.FoundingPostRequest;
import com.mercadolibre.desafio_spring.dtos.request.NewPostRequest;
import com.mercadolibre.desafio_spring.dtos.request.PromoPostRequest;
import com.mercadolibre.desafio_spring.dtos.response.*;
import com.mercadolibre.desafio_spring.entities.Donation;
import com.mercadolibre.desafio_spring.entities.FoundingPost;
import com.mercadolibre.desafio_spring.exceptions.AlreadyExistError;
import com.mercadolibre.desafio_spring.exceptions.IdNotFound;
import com.mercadolibre.desafio_spring.exceptions.SortedMethodError;
import org.springframework.http.HttpStatus;

public interface ISocialMeliService {
    HttpStatus follow(int userId, int userIdToFollow) throws IdNotFound ,AlreadyExistError;
    FollowersCountResponse followersCount(int userId) throws IdNotFound;
    FollowersListResponse followersList(int userId) throws IdNotFound;
    FollowedListResponse followedList(int UserId) throws IdNotFound;
    HttpStatus newPost(NewPostRequest newPostRequest) throws IdNotFound, AlreadyExistError;
    ListPostByUserResponse listPostUser(int userId) throws IdNotFound;
    void unfollow(int userId, int userIdToUnfollow)throws IdNotFound;
    void sortedFollowersUser(int userId, String sortedMet) throws SortedMethodError, IdNotFound;
    void sortedFollowedUser(int userId, String sortedMet) throws SortedMethodError, IdNotFound;
    void sortedPostUser(int userId, String sortedMet) throws SortedMethodError, IdNotFound;
    HttpStatus newPromoPost(PromoPostRequest promoPostRequest)throws IdNotFound, AlreadyExistError;
    PromoProductsCountResponse getPromoProductsCount(int userId)throws IdNotFound;
    ListPromoProductsResponse getPromoProductsList(int userid)throws IdNotFound;
    HttpStatus newDonationPost(Integer userId, FoundingPostRequest foundingPost) throws  IdNotFound, AlreadyExistError;
    HttpStatus newDonation(Integer userId, DonationRequest donation) throws IdNotFound;
    ListCampaingByUserResponse getCampaingsByUserId(Integer userId) throws IdNotFound;
}
