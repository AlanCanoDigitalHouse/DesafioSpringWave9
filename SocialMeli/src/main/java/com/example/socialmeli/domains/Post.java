package com.example.socialmeli.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post implements Comparable<Post>{

    private Integer idPost;
    private User user;
    private Date date;
    private Product detail;
    private String category;
    private Double price;

    public Post(User user, Date date, Product detail, String category, Double price) {
        this.user = user;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
    }

    @Override
    public int compareTo(Post o) {
        return date.compareTo(o.getDate());
    }
}
