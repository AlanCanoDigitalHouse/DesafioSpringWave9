package com.mercadolibre.socialmeli.dtos.posts.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponseDTO {

    private Integer userId;
    private Integer id_post;
    private LocalDate date;
    private ProductResponseDTO detail;
    private Integer category;
    private Double price;
    private Boolean hasPromo;
    private Double discount;

}
