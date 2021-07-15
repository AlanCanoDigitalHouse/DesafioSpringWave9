package com.jbianchini.meli.socialmeli.service.handler;

import com.jbianchini.meli.socialmeli.dto.UserDTO;
import com.jbianchini.meli.socialmeli.service.IUserService;
import org.springframework.stereotype.Component;

@Component
public class Initializer {
    //TODO: Include the database initilization here

    IUserService userService;

    public Initializer(IUserService userService) {
        this.userService = userService;
    }

    public void createAll() {

        UserDTO juan = (UserDTO) this.userService.createUser(new UserDTO("Juan")).getDto();
        UserDTO andres = (UserDTO) this.userService.createUser(new UserDTO("Andres")).getDto();
        UserDTO aldana = (UserDTO) this.userService.createUser(new UserDTO("Aldana")).getDto();
        UserDTO marialuz = (UserDTO) this.userService.createUser(new UserDTO("Maria Luz")).getDto();

        this.userService.follow(juan.getUserId(), andres.getUserId());
        this.userService.follow(juan.getUserId(), aldana.getUserId());
        this.userService.follow(juan.getUserId(), marialuz.getUserId());
        this.userService.follow(andres.getUserId(), marialuz.getUserId());
        this.userService.follow(marialuz.getUserId(), aldana.getUserId());


    }

}
