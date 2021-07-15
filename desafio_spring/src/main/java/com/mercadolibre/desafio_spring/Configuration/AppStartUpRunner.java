package com.mercadolibre.desafio_spring.Configuration;

import com.mercadolibre.desafio_spring.Repositories.ISocialMeliRepository;
import com.mercadolibre.desafio_spring.Services.ISocialMeliService;
import com.mercadolibre.desafio_spring.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AppStartUpRunner implements ApplicationRunner {
    @Autowired
    ISocialMeliRepository socialMeliRepository;

    @Autowired
    ISocialMeliService socialMeliService;

    User user1 = new User(1001,"aaabbb",new ArrayList<>(),new ArrayList<>(), new ArrayList<>());
    User user2 = new User(1002,"zzzzz",new ArrayList<>(),new ArrayList<>(), new ArrayList<>());
    User user3 = new User(1003,"xxxxx" ,new ArrayList<>(),new ArrayList<>(), new ArrayList<>());
    User user4 = new User(1004,"ccccc" ,new ArrayList<>(),new ArrayList<>(), new ArrayList<>());
    User user5 = new User(1005,"ppppp" ,new ArrayList<>(),new ArrayList<>(), new ArrayList<>());



    @Override
    public void run(ApplicationArguments args) throws Exception {
        socialMeliRepository.saveUser(user1);
        socialMeliRepository.saveUser(user2);
        socialMeliRepository.saveUser(user3);
        socialMeliRepository.saveUser(user4);
        socialMeliRepository.saveUser(user5);

        socialMeliService.follow(user1.getUserId(),user2.getUserId());
        socialMeliService.follow(user1.getUserId(),user3.getUserId());
        socialMeliService.follow(user1.getUserId(),user4.getUserId());
        socialMeliService.follow(user3.getUserId(),user2.getUserId());
        socialMeliService.follow(user4.getUserId(),user2.getUserId());
        socialMeliService.follow(user5.getUserId(),user2.getUserId());

    }
}
