package com.mercadolibre.socialmedia.dtos.request;


import com.mercadolibre.socialmedia.dtos.ProductDto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.ParseException;

public class PostPromoRequest extends PostRequestDto{
    @NotNull(message = "This field cant be null.")
    private Boolean hasPromo;

    @NotNull(message = "This field cant be null.")
    @Min(0L)
    private Double discount;

    public PostPromoRequest(Integer userId, String date, ProductDto detail, Integer category, Double price, Boolean hasPromo, Double discount) throws ParseException {
        super(userId, date, detail, category, price);
        this.hasPromo = hasPromo;
        this.discount = discount;
    }

    public void setHasPromo(Boolean hasPromo){
        this.hasPromo = hasPromo;
    }

    public void setDiscount(Double discount){
        this.discount = discount;
    }
    public Boolean getHasPromo(){
        return this.hasPromo;
    }
    public Double getDiscount(){
        return this.discount;
    }
}
