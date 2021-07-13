package com.example.socialmeli.dtos;

import com.example.socialmeli.dtos.requests.RequestProductDto;
import lombok.Data;

import java.util.Date;

@Data
public class PostDto {
    private Integer userId;
    private Date date;
    private RequestProductDto detail;
    private Integer category;
    private double price;
}
