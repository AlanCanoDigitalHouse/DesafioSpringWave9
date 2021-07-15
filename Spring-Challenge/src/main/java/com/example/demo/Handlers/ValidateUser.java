package com.example.demo.Handlers;

import com.example.demo.Exceptions.ExceptionHandler;
import com.example.demo.Exceptions.InvalidUserException;
import com.example.demo.Models.UserModel;
import com.example.demo.Repositories.IUserRepository;
import com.example.demo.Repositories.UserRepository;

import java.util.List;

public class ValidateUser {

    public static boolean validateUser(List<UserModel> users, int userId) throws ExceptionHandler {
        for(UserModel u:users){
            if(u.getUserId()==userId) {
                return true;
            }
        }
        throw new InvalidUserException();
    }

}
