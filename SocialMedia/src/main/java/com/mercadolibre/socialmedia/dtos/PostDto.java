package com.mercadolibre.socialmedia.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDto {
    private Integer id_post;
    private Date date;
    private ProductDto detail;
    private Integer category;
    private Double price;
    private Boolean hasPromo;
    private Double discount;

    public PostDto(Date date, ProductDto detail, Integer category, Double price) {
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
    }
}
