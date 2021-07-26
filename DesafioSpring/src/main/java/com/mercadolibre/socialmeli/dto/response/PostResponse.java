package com.mercadolibre.socialmeli.dto.response;

import com.mercadolibre.socialmeli.dto.ProductDto;
import com.mercadolibre.socialmeli.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PostResponse {
    private Integer postId;

    private Date date;

    private ProductDto detail;

    private Integer category;

    private Double price;
}
