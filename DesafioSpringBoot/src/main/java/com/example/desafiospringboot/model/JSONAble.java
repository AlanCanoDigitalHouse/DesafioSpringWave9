package com.example.desafiospringboot.model;

import org.json.simple.JSONObject;

public interface JSONAble {
    public JSONObject toJson();
    public void asimilar(JSONObject body);
}
