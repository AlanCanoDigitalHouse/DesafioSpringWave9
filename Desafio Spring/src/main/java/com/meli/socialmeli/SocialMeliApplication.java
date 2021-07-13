package com.meli.socialmeli;

import com.meli.socialmeli.controllers.UsersController;
import com.meli.socialmeli.exceptions.UserDoesNotExistException;
import com.meli.socialmeli.repositories.UsersRepository;
import com.meli.socialmeli.services.UsersService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SocialMeliApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialMeliApplication.class, args);
    }

}
