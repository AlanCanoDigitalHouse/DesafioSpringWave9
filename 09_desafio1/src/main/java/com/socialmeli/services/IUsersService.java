package com.socialmeli.services;

import com.socialmeli.dtos.ResponseDTO;
import com.socialmeli.dtos.request.SortEnum;

public interface IUsersService {

    /**
     * Service to follow a seller on SocialMeli
     * @param idUser id of the user you want to follow
     * @param idFollow user id to follow
     * @return OK status if follow was correct
     */
    ResponseDTO followUser(Integer idUser, Integer idFollow);

    /**
     * Service to unfollow a seller on SocialMeli
     * @param idUser id of the user you want to unfollow
     * @param idUnFollow user id to unfollow
     * @return OK status if unfollow was correct
     */
    ResponseDTO unfollowUser(Integer idUser, Integer idUnFollow);

    /**
     * Service of a user's customer counter followers
     * @param idUser id of the user to count his followers
     * @return followers counter
     */
    ResponseDTO countFollowers(Integer idUser);

    /**
     * Service to list the followers of a user
     * @param idUser idUser id of the user to list his followers
     * @param sort ordering of users by name
     * @return list of followers
     */
    ResponseDTO listFollowers(Integer idUser, SortEnum sort);

    /**
     * Service to list the followed of a user
     * @param idUser idUser id of the user to list his followed
     * @param sort ordering of users by name
     * @return list of followed
     */
    ResponseDTO listFollowed(Integer idUser, SortEnum sort);
}
