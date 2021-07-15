package com.example.demo.services.interfaces;

import com.example.demo.dtos.response.ResponseOkDto;
import com.example.demo.models.User;

import java.util.Map;

public interface UserService {

    public User getUser(int userId);

    public Map<Integer, User> getUsers();

    public ResponseOkDto addUser(int userId , String userName);

}
