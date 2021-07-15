package com.example.desafiospringboot.model;


import org.json.simple.JSONObject;

import java.util.LinkedHashMap;

public class Post implements JSONAble {
    private int userId;
    private int id_post;
    private double price;
    private int category;
    private PostDetail detail;
    private String date;
    public Post( int idAutor){
        this.userId = idAutor;
    }
    public Post(){
        super();
    }
    public JSONObject toJson(){
        JSONObject retorno = new JSONObject();
        retorno.put("userId", this.userId);
        retorno.put("id_post", this.id_post);
        retorno.put("date", this.date);
        retorno.put("detail",this.detail.toJson());
        retorno.put("price", this.price);
        retorno.put("category", this.category);
        return retorno;
    }
    @Override
    public void asimilar(JSONObject body) {
        int a = (Integer) body.get("userId");
        int b = (Integer) body.get("id_post");
        int c = (Integer) body.get("category");
        this.userId = a;
        this.id_post = b;
        this.price = (Double) body.get("price"); 
        this.category = c;
        this.detail = new PostDetail();

        this.detail.asimilar(new JSONObject( (LinkedHashMap) body.get("detail")));
        this.date = (String) body.get("date");
        // TODO Auto-generated method stub
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getId_post() {
        return id_post;
    }
    public void setId_post(int id_post) {
        this.id_post = id_post;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getCategory() {
        return category;
    }
    public void setCategory(int category) {
        this.category = category;
    }
    public PostDetail getDetail() {
        return detail;
    }
    public void setDetail(PostDetail detail) {
        this.detail = detail;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    
}
