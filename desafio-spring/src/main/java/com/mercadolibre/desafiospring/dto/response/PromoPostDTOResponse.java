package com.mercadolibre.desafiospring.dto.response;

import com.mercadolibre.desafiospring.dto.request.ProductDTO;
import com.mercadolibre.desafiospring.model.Product;
import com.mercadolibre.desafiospring.util.DateUtils;
import lombok.*;
import java.util.Date;

@Setter
@Getter
public class PromoPostDTOResponse {
    String date;
    ProductDTO detail;
    String category;
    double price;
    boolean hasPromo;
    double discount;

    public PromoPostDTOResponse(Date date, Product product, int category,
                                double price, double discount) {
        this.date = DateUtils.dateFormat.format(date);
        this.detail = new ProductDTO(product);
        this.category = String.valueOf(category);
        this.price = price;
        this.hasPromo = true;
        this.discount = discount;
    }
}
