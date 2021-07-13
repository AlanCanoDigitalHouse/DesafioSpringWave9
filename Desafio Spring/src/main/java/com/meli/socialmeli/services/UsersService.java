package com.meli.socialmeli.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.socialmeli.models.User;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class UsersService implements IUsersService{
    @Override
    public boolean validateUserId(int userId) {
        return false;
    }

    @Override
    public List<User> orderNameAsc(List<User> users) {
        return null;
    }

    @Override
    public List<User> orderNameDesc(List<User> users) {
        return null;
    }
}
