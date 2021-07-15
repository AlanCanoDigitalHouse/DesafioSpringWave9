package com.mercadolibre.socialmeli.service;

import com.mercadolibre.socialmeli.converter.UserToUserDtoConverter;
import com.mercadolibre.socialmeli.dto.ListUserFollowDto;
import com.mercadolibre.socialmeli.entity.User;
import com.mercadolibre.socialmeli.exception.OrderException;
import com.mercadolibre.socialmeli.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService{
    public final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Integer userId) {
        User user = new User (userRepository.getById(userId));
        return new User(user.getUserId(),user.getUserName());
    }
    @Override
    public List<ListUserFollowDto> listUser(List<Integer> userId) {
        UserToUserDtoConverter userDto = new UserToUserDtoConverter();
        List<User> user = userId.stream().map((f) -> findById(f)).collect(Collectors.toList());
        return user.stream().sequential().map(userDto::convert).collect(Collectors.toList());
    }



}
