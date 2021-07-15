package com.mercadolibre.socialmeli.repositories.interfaces;

import com.mercadolibre.socialmeli.dtos.users.UserFollowsRespDTO;

public interface IUserRepository {
    UserFollowsRespDTO find(Integer id);
}
