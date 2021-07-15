package com.example.desafiospringboot.model;

import org.json.simple.JSONObject;

public class Usuario implements JSONAble {
    private int userId;
    private String userName;
    public JSONObject toJson(){
        JSONObject retorno = new JSONObject();
        retorno.put("userId", this.userId);
        retorno.put("userName", this.userName);
        return retorno;
    }
    public Usuario(int id, String name){
        this.userId = id;
        this.userName = name;
    }
    public Usuario(JSONObject info){
        this.userName = (String) info.get("userName");
        Long a = (Long) info.get("userId"); 
        int b = a.intValue();
        this.userId = b;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Override
    public void asimilar(JSONObject body) {
        // TODO Auto-generated method stub
        
    }
    
}
