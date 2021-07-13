package com.socialMeli.controller;

import com.socialMeli.model.UserModel;
import com.socialMeli.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private UserRepository userRepository;

}
