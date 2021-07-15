package com.jbianchini.meli.socialmeli.repository;

import com.jbianchini.meli.socialmeli.model.User;

public interface IUserRepository {
    /**
     * Saves a user.
     *
     * @param user {@link User} to save
     * @return the saved user
     */
    User save(User user);

    /**
     * Finds a user by its id
     *
     * @param userId user id
     * @return a {@link User}
     */
    User findByUserId(Integer userId);

    /**
     * Deletes a user
     *
     * @param user to be deleted
     */
    void delete(User user);
}
