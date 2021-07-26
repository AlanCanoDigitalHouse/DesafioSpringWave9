package com.example.demo.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@AllArgsConstructor
public class Post {
    private int postId;
    private int userId;
    private Date date;
    private Product detail;
    private double price;
    private int category;
}
