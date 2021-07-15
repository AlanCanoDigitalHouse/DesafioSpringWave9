package com.meli.itbootcamp.dto;

import com.meli.itbootcamp.model.User;
import lombok.*;

@Data
@Getter
@Setter
public class CountPromoDTO{
    private Integer promoproducts_count;
    private Integer userId;
    private String userName;
    public CountPromoDTO(User seller, Integer quant){
        this.userId =seller.getUserID();
        this.userName= seller.getUserName();
        this.promoproducts_count=quant;
    }

    public CountPromoDTO() {

    }
}
