package com.mercadolibre.socialmeli.logic;


import com.mercadolibre.socialmeli.converter.UserToUserDtoConverter;
import com.mercadolibre.socialmeli.dto.ListUserFollowDto;
import com.mercadolibre.socialmeli.dto.UserDto;
import com.mercadolibre.socialmeli.entity.Follow;
import com.mercadolibre.socialmeli.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserUtils {


    public List<ListUserFollowDto> listUser(List<User> listUser){
        UserToUserDtoConverter userToUserDtoConverter = new UserToUserDtoConverter();
        return listUser.stream().map(userToUserDtoConverter::convert).collect(Collectors.toList());
    }


}
