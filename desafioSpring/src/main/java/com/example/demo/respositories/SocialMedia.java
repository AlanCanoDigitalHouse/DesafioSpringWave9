package com.example.demo.respositories;

import com.example.demo.dtos.response.UserDto;
import com.example.demo.exceptions.UserNotFound;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface SocialMedia<T> {

    List<T> loadDatabase() throws FileNotFoundException;

    List<T> mapObject(File file);

}
