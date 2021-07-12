package com.example.desafiospring.services.implementation;

import com.example.desafiospring.dtos.UserDto;
import com.example.desafiospring.entities.User;
import com.example.desafiospring.repositories.IUserRepository;
import com.example.desafiospring.services.IUserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService implements IUserService {

    private IUserRepository userRepository;
    private ObjectMapper objectMapper;

    public UserService(IUserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public UserDto findByUserId(Long userId, boolean isSeller) {
        User user = this.userRepository.findById(userId, isSeller);
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
            response = this.objectMapper.convertValue(user, new TypeReference<List<UserDto>>(){});
        return response;
    }
}
