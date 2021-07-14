package com.example.desafiospringboot.model;

import org.json.simple.JSONObject;

public class Post {
    private String contenido;
    private int idAutor;
    public Post(String contenido, int idAutor){
        this.contenido = contenido;
        this.idAutor = idAutor;
    }
    public JSONObject toJson(){
        JSONObject retorno = new JSONObject();
        retorno.put("contenido", this.contenido);
        retorno.put("idAutor", this.idAutor);
        return retorno;
    }
}
