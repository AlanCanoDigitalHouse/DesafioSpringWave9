package com.socialmeli.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Integer userId;
    private Integer postId;
    private Date date;
    //private LocalDate date;
    private ProductDTO detail;
    private Integer category;
    private Double price;

}
