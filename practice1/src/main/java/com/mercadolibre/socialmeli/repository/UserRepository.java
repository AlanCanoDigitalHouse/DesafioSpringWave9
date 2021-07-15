package com.mercadolibre.socialmeli.repository;

import com.mercadolibre.socialmeli.dto.FollowDTO;
import com.mercadolibre.socialmeli.dto.UserDTO;
import com.mercadolibre.socialmeli.exception.EntityException;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<UserDTO> findAll();

    UserDTO save(UserDTO user);

    FollowDTO saveFollow(FollowDTO follow) throws EntityException;

    void deleteFollow(FollowDTO followDTO);

    Optional<FollowDTO> findFollow(Integer followerId, Integer followedId);

    Optional<UserDTO> findUserByUserId(Integer userId);

    List<UserDTO> findUserFollowers(Integer userId);

    List<UserDTO> findUserFollowed(Integer userId);

    void delete(UserDTO userDTO);
}
