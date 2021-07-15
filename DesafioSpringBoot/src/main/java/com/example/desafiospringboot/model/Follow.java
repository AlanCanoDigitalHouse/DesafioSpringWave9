package com.example.desafiospringboot.model;

import org.json.simple.JSONObject;

public class Follow implements JSONAble{
    private int follower;
    private int followed;
    /**
     *
     * @param a
     * el seguidor
     * @param b
     * el seguido
     */
    public Follow(int a, int b){
        this.followed = b;
        this.follower = a;
    }
    public JSONObject toJson(){
        JSONObject retorno = new JSONObject();
        retorno.put("follower", this.follower);
        retorno.put("followed", this.followed);
        return retorno;
    }
    @Override
    public void asimilar(JSONObject body) {
        Long a = (Long) body.get("followed");
        Long b = (Long) body.get("follower");
        this.followed = a.intValue();
        this.follower = b.intValue();
        // TODO Auto-generated method stub

    }
}

