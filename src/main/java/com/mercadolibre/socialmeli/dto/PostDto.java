package com.mercadolibre.socialmeli.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Integer userId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;

    private Integer productId;

    private Integer category;

    private Double price;

    public PostDto(Integer userId, Date date, Integer category, Double price) {
        this.userId = userId;
        this.date = date;
        this.category = category;
        this.price = price;
    }
}
