package com.meli.socialmeli;

import com.meli.socialmeli.repositories.PostsRepository;
import com.meli.socialmeli.repositories.UsersRepository;
import com.meli.socialmeli.services.UsersService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SocialMeliApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialMeliApplication.class, args);
        UsersRepository usersRepository = new UsersRepository();
        System.out.println(usersRepository.loadFollowDataBase());
    }

}
