package com.mercadolibre.desafio_spring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@Data
public class FoundingPost extends Post{
    Boolean isFounding;
    Double expectativeMoney;
    Double currentMoney;
    ArrayList<Donation> donations;



    public FoundingPost(Integer userId, Integer id_post, Date date, Product detail, Integer category, Double price, Boolean isFounding, Double expectativeMoney, Double currentMoney) {
        super(userId, id_post, date, detail, category, price);
        this.isFounding = isFounding;
        this.expectativeMoney = expectativeMoney;
        this.currentMoney = currentMoney;
        this.donations = new ArrayList<>();
    }

    public Boolean getFounding() {
        return isFounding;
    }

    public void setFounding(Boolean founding) {
        isFounding = founding;
    }

    public Double getExpectativeMoney() {
        return expectativeMoney;
    }

    public void setExpectativeMoney(Double expectativeMoney) {
        this.expectativeMoney = expectativeMoney;
    }

    public Double getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(Double currentMoney) {
        this.currentMoney = currentMoney;
    }

    public ArrayList<Donation> getDonations() {
        return donations;
    }

    public void setDonations(ArrayList<Donation> donations) {
        this.donations = donations;
    }
}
