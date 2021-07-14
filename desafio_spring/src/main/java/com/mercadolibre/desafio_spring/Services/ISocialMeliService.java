package com.mercadolibre.desafio_spring.Services;

import com.mercadolibre.desafio_spring.dtos.request.NewPostRequest;
import com.mercadolibre.desafio_spring.dtos.request.PromoPostRequest;
import com.mercadolibre.desafio_spring.dtos.response.*;
import org.springframework.http.HttpStatus;

public interface ISocialMeliService {
    HttpStatus follow(int userId, int userIdToFollow);
    FollowersCountResponse followersCount(int userId);
    FollowersListResponse followersList(int userId);
    FollowedListResponse followedList(int UserId);
    HttpStatus newPost(NewPostRequest newPostRequest);
    ListPostByUserResponse listPostUser(int userId);
    void unfollow(int userId, int userIdToUnfollow);
    void sortedFollowersUser(int userId, String sortedMet);
    void sortedFollowedUser(int userId, String sortedMet);
    void sortedPostUser(int userId, String sortedMet);
    HttpStatus newPromoPost(PromoPostRequest promoPostRequest);
    PromoProductsCountResponse getPromoProductsCount(int userId);
    ListPromoProductsResponse getPromoProductsList(int userid);
}
