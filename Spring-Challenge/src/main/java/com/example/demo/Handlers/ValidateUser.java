package com.example.demo.Handlers;

import com.example.demo.Exceptions.CustomExceptionHandler;
import com.example.demo.Exceptions.InvalidUserCustomException;
import com.example.demo.Models.UserModel;

import java.util.List;

public class ValidateUser {

    public static boolean validateUser(List<UserModel> users, int userId) throws CustomExceptionHandler {
        for(UserModel u:users){
            if(u.getUserId()==userId) {
                return true;
            }
        }
        throw new InvalidUserCustomException();
    }

}
