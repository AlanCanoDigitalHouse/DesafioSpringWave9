package com.jbianchini.meli.socialmeli.service;

import com.jbianchini.meli.socialmeli.dto.FollowedListDTO;
import com.jbianchini.meli.socialmeli.dto.FollowersCountDTO;
import com.jbianchini.meli.socialmeli.dto.FollowersListDTO;
import com.jbianchini.meli.socialmeli.dto.UserDTO;
import com.jbianchini.meli.socialmeli.dto.response.ResponseDTO;
import com.jbianchini.meli.socialmeli.exception.ApplicationException;
import com.jbianchini.meli.socialmeli.model.User;

public interface IUserService {

    /**
     * Creates a new user.
     *
     * @param userDTO dto containing new user information
     * @return ResponseDTO with the response status and the argument data
     */
    ResponseDTO createUser(UserDTO userDTO);

    /**
     * Finds a user by its id
     *
     * @param userId user id
     * @return a {@link User}
     */
    User findByUserId(Integer userId);

    /**
     * Add the user to follow to the user's followed list, and add the follower user to the followed user's follower
     * list.
     *
     * @param userId         follower user id
     * @param userIdToFollow followed user id
     * @return ResponseDTO with the response status and the argument data
     * @throws ApplicationException
     */
    ResponseDTO follow(Integer userId, Integer userIdToFollow);

    /**
     * Gets the amount of users following the user with the id passed.
     *
     * @param userId followed user id
     * @return a {@link FollowersCountDTO} with the count information
     */
    FollowersCountDTO getFollowersCount(Integer userId);

    /**
     * Retrieves the list of followers of the user id passed as argument, in certain order. The default order is
     * ascending.
     *
     * @param userID followed user id
     * @param order  String specifying order
     * @return a {@link FollowersListDTO} with the followers
     */
    FollowersListDTO getFollowers(Integer userID, String order);

    /**
     * Retrieves the list of followed users of the user id passed as argument, in certain order. The default order is
     * ascending.
     *
     * @param userID follower user id
     * @param order  String specifying order
     * @return a {@link FollowedListDTO} with the followed users
     */
    FollowedListDTO getFollowed(Integer userID, String order);

    /**
     * Removes the user to unfollow from the user's followed list, and removes the follower user from the followed
     * user's follower list.
     *
     * @param userId           follower user id
     * @param userIdToUnfollow followed user id
     * @return ResponseDTO with the response status and the argument data
     */
    ResponseDTO unFollow(Integer userId, Integer userIdToUnfollow);
}
