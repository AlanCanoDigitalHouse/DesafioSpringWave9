package com.mercadolibre.socialmeli.converter;

import com.mercadolibre.socialmeli.dto.request.FollowRequest;
import com.mercadolibre.socialmeli.entity.Follow;
import com.mercadolibre.socialmeli.repository.IUserRepository;
import org.springframework.core.convert.converter.Converter;

public class FollowRequestToFollowConverter implements Converter<FollowRequest, Follow> {
    public final IUserRepository userRepository;

    public FollowRequestToFollowConverter(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Follow convert(FollowRequest followRequest) {

        return    null;
    }
}
