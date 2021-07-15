package com.example.socialmeli.handlers;

import com.example.socialmeli.models.User;
import com.example.socialmeli.repositories.UserRepository;

public class ServiceHandler {

    public static void initializeUsers(UserRepository userRepository) {
        User user_1 = new User(1,"Carlos");
        User user_2 = new User(2,"Marta");
        User user_3 = new User(3,"Jose");
        User user_4 = new User(4,"Manuela");
        userRepository.add(user_1);
        userRepository.add(user_2);
        userRepository.add(user_3);
        userRepository.add(user_4);
    }

}
