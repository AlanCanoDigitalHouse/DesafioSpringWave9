package com.mercadolibre.desafiospring.dto.response;

import com.mercadolibre.desafiospring.dto.request.*;
import com.mercadolibre.desafiospring.model.*;
import com.mercadolibre.desafiospring.util.DateUtils;
import lombok.*;

@Getter
@Setter
public class PostDTOResponse {
    private String date;
    private ProductDTO detail;
    private String category;
    private double price;

    public PostDTOResponse(Post post) {
        this.date = DateUtils.dateFormat.format(post.getDate());
        this.detail = new ProductDTO(post.getProduct());
        this.category = String.valueOf(post.getCategory());
        this.price = post.getPrice();
    }
}
