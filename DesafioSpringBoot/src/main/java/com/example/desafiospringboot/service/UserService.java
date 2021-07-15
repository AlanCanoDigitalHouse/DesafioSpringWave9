package com.example.desafiospringboot.service;

import com.example.desafiospringboot.model.Usuario;
import org.json.simple.JSONArray;

public interface UserService {
    public Boolean usrExist(int usr);
    public JSONArray usrList();
    public Usuario getUser(int userId);
}
