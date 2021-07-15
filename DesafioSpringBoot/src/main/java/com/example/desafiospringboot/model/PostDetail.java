package com.example.desafiospringboot.model;

import org.json.simple.JSONObject;

public class PostDetail implements JSONAble {
    private int product_id;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;

    @Override
    public JSONObject toJson() {
        JSONObject retorno = new JSONObject();
        retorno.put("product_id", this.product_id);
        retorno.put("type", product_id);
        retorno.put("color", this.color);
        retorno.put("brand", this.brand);
        retorno.put("notes", this.notes);
        retorno.put("productName", this.productName);
        
        return retorno;
    }

    @Override
    public void asimilar(JSONObject body) {
        // TODO Auto-generated method stub
        int a = (Integer) body.get("product_id");
        this.product_id = a;
        this.productName = (String) body.get("productName");
        this.type = (String) body.get("type");
        this.brand = (String) body.get("brand");
        this.color = (String) body.get("color");
        this.notes = (String) body.get("notes");
    }
    
}
