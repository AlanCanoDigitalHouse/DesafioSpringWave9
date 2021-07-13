package com.example.desafiospring.services.implementation;

import com.example.desafiospring.dtos.UserDto;
import com.example.desafiospring.entities.User;
import com.example.desafiospring.exceptions.UserNotExistException;
import com.example.desafiospring.repositories.IUserRepository;
import com.example.desafiospring.services.IUserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final ObjectMapper objectMapper;

    public UserService(IUserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public UserDto findByUserIdAndType(Long userId, boolean isSeller) {
        User user = this.userRepository.findByIdAndType(userId, isSeller);
        UserDto response = null;
        if (Objects.nonNull(user))
            response = this.objectMapper.convertValue(user, UserDto.class);
        return response;
    }

    @Override
    public UserDto findByUserId(Long userId) {
        User user = this.userRepository.findById(userId);
        UserDto response = null;
        if (Objects.nonNull(user))
            response = this.objectMapper.convertValue(user, UserDto.class);
        return response;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> user = this.userRepository.getAllUsers();
        List<UserDto> response = null;
        if (Objects.nonNull(user))
            response = this.objectMapper.convertValue(user, new TypeReference<>() {
            });
        return response;
    }

    @Override
    public UserDto validateSellerExist(Long userId) throws UserNotExistException {
        UserDto user = this.findByUserIdAndType(userId, true);
        if (Objects.isNull(user))
            throw new UserNotExistException("No existe un vendedor con el id " + userId);
        return user;
    }

    @Override
    public UserDto validateUserExist(Long userId) throws UserNotExistException {
        UserDto user = this.findByUserId(userId);
        if (Objects.isNull(user))
            throw new UserNotExistException("No existe un usuario con el id " + userId);
        return user;
    }
}
