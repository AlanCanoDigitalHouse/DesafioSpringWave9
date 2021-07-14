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

    User user1 = new User(1001,"Usuario1",new ArrayList<>(),new ArrayList<>(), new ArrayList<>());
    User user2 = new User(1002,"Usuario2",new ArrayList<>(),new ArrayList<>(), new ArrayList<>());
    User user3 = new User(1003,"usuario3" ,new ArrayList<>(),new ArrayList<>(), new ArrayList<>());

    @Override
    public void run(ApplicationArguments args) throws Exception {
        socialMeliRepository.saveUser(user1);
        socialMeliRepository.saveUser(user2);
        socialMeliRepository.saveUser(user3);
    }
}
