package com.example.demo.services;

import com.example.demo.dtos.response.ResponseOkDto;
import com.example.demo.exceptions.userExceptions.Exceptions.UserNotFoundException;
import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseOkDto addUser(int userId, String userName) {
        userRepository.addUser(userId , userName);
        return new ResponseOkDto(HttpStatus.OK.value() , "Succesfuly user create");
    }

    @Override
    public User getUser(int userId) {
        User user = userRepository.getUser(userId);
        return user;
    }

    @Override
    public Map<Integer, User> getUsers() {
        return userRepository.getUsers();
    }
}
