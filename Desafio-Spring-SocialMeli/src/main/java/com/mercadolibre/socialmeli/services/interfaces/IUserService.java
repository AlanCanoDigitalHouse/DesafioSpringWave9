package com.mercadolibre.socialmeli.services.interfaces;

import com.mercadolibre.socialmeli.dtos.users.UserDTO;
import com.mercadolibre.socialmeli.dtos.users.VendorFollowQtyRespDTO;
import com.mercadolibre.socialmeli.dtos.users.UserFollowsRespDTO;
import com.mercadolibre.socialmeli.exceptions.users.SameUserTwiceException;
import com.mercadolibre.socialmeli.exceptions.users.UserAlreadyFollowException;
import com.mercadolibre.socialmeli.exceptions.users.UserAlreadyUnfollowException;
import com.mercadolibre.socialmeli.exceptions.users.UserNotFoundException;

public interface IUserService {
    String followVendor(Integer userId, Integer userIdToFollow)
            throws UserAlreadyFollowException, SameUserTwiceException, UserNotFoundException, UserAlreadyUnfollowException;

    VendorFollowQtyRespDTO countVendorFollows(Integer userId)
            throws UserNotFoundException;

    UserFollowsRespDTO allVendorFollowers(Integer userID,String order)
            throws UserNotFoundException;

    UserFollowsRespDTO allUserFollowed(Integer userID, String order)
            throws UserNotFoundException;

    String unfollowVendor(Integer userId, Integer userIdToUnfollow)
            throws SameUserTwiceException, UserNotFoundException,
            UserAlreadyFollowException, UserAlreadyUnfollowException;

    UserDTO findUserData(Integer userId) throws UserNotFoundException;
}
