package com.socialmeli.repositories;

import com.socialmeli.models.User;
import com.socialmeli.models.UserSocial;

import java.util.ArrayList;
import java.util.List;

public interface ISocialRepository {

    /**
     * Search user in the repository from the repository ID
     * @param Id post id to be searched
     * @return user found
     */
    UserSocial findUserbyId(Integer Id);

    /**
     * Save a new user in the repository
     * @param user user to save
     * @return saved user
     */
    UserSocial saveUser(UserSocial user);

    /***
     * Removes a user from the repository
     * @param user user to be deleted
     */
    void delete(UserSocial user);


}
