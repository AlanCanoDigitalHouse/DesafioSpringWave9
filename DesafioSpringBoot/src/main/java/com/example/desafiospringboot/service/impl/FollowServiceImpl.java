package com.example.desafiospringboot.service.impl;

import com.example.desafiospringboot.dao.DaoFollow;
import com.example.desafiospringboot.dao.DaoUsuarios;
import com.example.desafiospringboot.dto.FollowCount;
import com.example.desafiospringboot.dto.FollowersDetail;
import com.example.desafiospringboot.model.Follow;
import com.example.desafiospringboot.model.Usuario;
import com.example.desafiospringboot.service.FollowService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    private DaoFollow daoFollow;

    @Autowired
    private DaoUsuarios daoUsuarios;

    @Override
    public boolean seguir(int seguidor, int seguido) {
        Follow f = new Follow(seguidor, seguido);
        daoFollow.createFollow(f);
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public FollowCount getFollowersCount(int usuario) {
        List a = daoFollow.followersList(usuario);
        FollowCount retorno = new FollowCount();
        retorno.setFollowers_count(a.size());
        retorno.setUserId(usuario);
        retorno.setUserName(daoUsuarios.getUser(usuario).getUserName());
        // TODO Auto-generated method stub
        return retorno;
    }

    @Override
    public FollowersDetail getFollowersDetail(int usuario) {
        List <Integer> a = daoFollow.followersList(usuario);
        JSONArray retorno = new JSONArray();
        for (int i = 0; i < a.size(); i++) {
            Usuario usu = daoUsuarios.getUser(a.get(i));
            JSONObject temp = usu.toJson();
            retorno.add(temp);
        }
        FollowersDetail payload  = new FollowersDetail();
        payload.setUserName(daoUsuarios.getUser(usuario).getUserName());
        payload.setUserId(usuario);
        payload.setFollowers(retorno);
        // TODO Auto-generated method stub
        return payload;
    }

    @Override
    public FollowersDetail getFollowedDetail(int usuario) {
        List <Integer> a = daoFollow.followedList(usuario);
        JSONArray retorno = new JSONArray();
        for (int i = 0; i < a.size(); i++) {
            Usuario usu = daoUsuarios.getUser(a.get(i));
            JSONObject temp = usu.toJson();
            retorno.add(temp);
        }

        FollowersDetail payload  = new FollowersDetail();
        payload.setUserName(daoUsuarios.getUser(usuario).getUserName());
        payload.setUserId(usuario);
        payload.setFollowers(retorno);
        // TODO Auto-generated method stub
        return payload;
    }
    public FollowersDetail getFollowersDetailOrdered(int usuario, boolean asc){
        List <Integer> a = daoFollow.followersList(usuario);
        JSONArray retorno = new JSONArray();
        ArrayList <String> listaNombres = new ArrayList<String>();
        for (int i = 0; i < a.size(); i++) {
            Usuario usu = daoUsuarios.getUser(a.get(i));
            //JSONObject temp = usu.toJson();
            //retorno.add(temp);
            listaNombres.add(usu.getUserName().toLowerCase());
        }
        if(asc){
            Collections.sort(listaNombres);
        }else{
            Collections.sort(listaNombres, Collections.reverseOrder());
        }
        System.out.println("Nombres ordenados - "+listaNombres);
        for (int i = 0; i < listaNombres.size(); i++) {
            for (int j = 0; j < a.size(); j++) {
                Usuario usu = daoUsuarios.getUser(a.get(j));
                if(usu.getUserName().equals(listaNombres.get(i))){
                    JSONObject temp = usu.toJson();
                    retorno.add(temp);
                }
            }
        }
        FollowersDetail payload  = new FollowersDetail();
        payload.setUserName(daoUsuarios.getUser(usuario).getUserName());
        payload.setUserId(usuario);
        payload.setFollowers(retorno);
        // TODO Auto-generated method stub
        return payload;
    }

    @Override
    public boolean unfollow(int seguidor, int seguido) {
        return daoFollow.unFollow(Long.valueOf(seguidor), Long.valueOf(seguido));
    }

    @Override
    public FollowersDetail getFollowedDetailOrdered(int usuario, boolean asc) {
        List <Integer> a = daoFollow.followedList(usuario);
        JSONArray retorno = new JSONArray();
        ArrayList <String> listaNombres = new ArrayList<String>();
        for (int i = 0; i < a.size(); i++) {
            Usuario usu = daoUsuarios.getUser(a.get(i));
            listaNombres.add(usu.getUserName().toLowerCase());
        }
        if(asc){
            Collections.sort(listaNombres);
        }else{
            Collections.sort(listaNombres, Collections.reverseOrder());
        }
        System.out.println("Nombres ordenados - "+listaNombres);
        for (int i = 0; i < listaNombres.size(); i++) {
            for (int j = 0; j < a.size(); j++) {
                Usuario usu = daoUsuarios.getUser(a.get(j));
                if(usu.getUserName().equals(listaNombres.get(i))){
                    JSONObject temp = usu.toJson();
                    retorno.add(temp);
                }
            }
        }
        FollowersDetail payload  = new FollowersDetail();
        payload.setUserName(daoUsuarios.getUser(usuario).getUserName());
        payload.setUserId(usuario);
        payload.setFollowers(retorno);
        return payload;
    }
    
}
