package com.example.desafiospringboot.model;

import org.json.simple.JSONObject;

/**
 * PromoPost
 */
public class PromoPost extends Post {
    private Boolean hasPromo;
    private double discount;
    public Boolean getHasPromo() {
        return hasPromo;
    }
    public void setHasPromo(Boolean hasPromo) {
        this.hasPromo = hasPromo;
    }
    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    @Override
    public void asimilar(JSONObject body){
        super.asimilar(body);
        this.hasPromo = (Boolean) body.get("hasPromo");
        this.discount = (Double) body.get("discount");
    }  
    @Override
    public JSONObject toJson(){
        JSONObject ob = super.toJson();
        ob.put("hasPromo", this.hasPromo);
        ob.put("discount", this.discount);
        return ob;
    }
}