package com.mercadolibre.social_meli.service;

public interface IUserService {

    void followUser(Integer userId, Integer userIdToFollow);

    void unfollowUser(Integer userId, Integer userIdToUnfollow);

}
