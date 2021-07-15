package com.mercadolibre.socialmeli.service;

import com.mercadolibre.socialmeli.dto.ListUserFollowDto;
import com.mercadolibre.socialmeli.dto.UserDto;
import com.mercadolibre.socialmeli.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService  {
    public User findById(Integer userId);
    public List<ListUserFollowDto> listUser(List<Integer> list);

}
