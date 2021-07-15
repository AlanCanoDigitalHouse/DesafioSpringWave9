package com.example.desafiospringboot.controller;

import com.example.desafiospringboot.service.impl.UserServiceImpl;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
    @Autowired
    private UserServiceImpl daoUsuarios;

    @GetMapping("/users/all")
    public JSONArray getAllUsers(){
        return daoUsuarios.usrList();
    }
}
