package com.mercadolibre.desafio_spring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class PromoPost extends Post{

    boolean hasPromo;
    double discount;

    public PromoPost(int userId, int id_post, Date date, Product detail, int category, double price, boolean hasPromo, double discount) {
        super(userId, id_post, date, detail, category, price);
        this.hasPromo = hasPromo;
        this.discount = discount;
    }

    public boolean isHasPromo() {
        return hasPromo;
    }

    public void setHasPromo(boolean hasPromo) {
        this.hasPromo = hasPromo;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
