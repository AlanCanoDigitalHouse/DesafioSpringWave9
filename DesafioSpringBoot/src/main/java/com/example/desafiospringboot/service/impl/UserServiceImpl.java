package com.example.desafiospringboot.service.impl;

import com.example.desafiospringboot.dao.DaoUsuarios;
import com.example.desafiospringboot.model.Usuario;
import com.example.desafiospringboot.service.UserService;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private DaoUsuarios daoUsuarios;

    @Override
    public Boolean usrExist(int usr) {
        Usuario data = daoUsuarios.getUser(usr);
        if(data==null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public JSONArray usrList() {
        return daoUsuarios.loadUsers();
    }

    @Override
    public Usuario getUser(int userId) {
        // TODO Auto-generated method stub
        return daoUsuarios.getUser(userId);
    }
    
}
