package com.mercadolibre.socialmeli.converter;


import com.mercadolibre.socialmeli.dto.ListUserFollowDto;
import com.mercadolibre.socialmeli.dto.UserDto;
import com.mercadolibre.socialmeli.entity.User;
import org.springframework.core.convert.converter.Converter;

public class UserToUserDtoConverter implements Converter<User, ListUserFollowDto> {
    @Override
    public ListUserFollowDto convert(User user) {
        return ListUserFollowDto.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .build();
    }
}
