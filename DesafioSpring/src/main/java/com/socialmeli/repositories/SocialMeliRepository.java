package com.socialmeli.repositories;

import com.socialmeli.dtos.UserDTO;

import java.util.Optional;

public interface SocialMeliRepository {

    UserDTO save(UserDTO userId);
    Optional<UserDTO> findUserByUserId(Integer userId);
    Integer lastPostId();
    Integer lastProductId();

}
