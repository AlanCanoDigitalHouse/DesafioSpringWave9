package com.socialMeli.service;

import com.socialMeli.dto.response.CountFollowersResponseDTO;
import com.socialMeli.dto.response.FollowResultResponseDTO;
import com.socialMeli.dto.response.UserFollowedResponseDTO;
import com.socialMeli.dto.response.UserFollowersResponseDTO;
import com.socialMeli.exception.exception.*;

public interface IUserService {
    FollowResultResponseDTO follow(int userId, int userIdToFollow) throws ModelNotExists, FollowHimselfException, AlreadyFollowedException;

    CountFollowersResponseDTO getCountOfFollowers(int idUser) throws ModelNotExists;

    UserFollowersResponseDTO getListFollowers(int idUser, String order) throws ModelNotExists, OrderNotValidException;

    UserFollowedResponseDTO getListUsersFollowed(int idUser, String order) throws ModelNotExists, OrderNotValidException;

    void unfollowUser(int userID, int userIdToUnfollow) throws ModelNotExists, UserDontFollowThisUser;
}
