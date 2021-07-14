package com.socialmeli.models;

import lombok.Data;

import java.util.Date;

@Data
public class PostSocial {

    private Integer id;
    private Date date;
    private ProductSocial detail;
    private Integer category;
    private Float price;
}
