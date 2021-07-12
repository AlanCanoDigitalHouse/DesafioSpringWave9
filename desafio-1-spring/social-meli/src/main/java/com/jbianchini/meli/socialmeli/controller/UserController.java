package com.jbianchini.meli.socialmeli.controller;

import com.jbianchini.meli.socialmeli.exception.ApplicationException;
import com.jbianchini.meli.socialmeli.model.UserDTO;
import com.jbianchini.meli.socialmeli.service.IUserService;
import com.jbianchini.meli.socialmeli.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/users")
public class UserController {

    IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PutMapping("/create")
    public UserDTO create(@RequestBody UserDTO user){
        return this.userService.create(user);
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public void follow(@PathVariable int userId, @PathVariable int userIdToFollow, HttpServletResponse response)
            throws ApplicationException {
        this.userService.follow(userId, userIdToFollow);
        response.setStatus(HttpServletResponse.SC_OK);
    }


}
