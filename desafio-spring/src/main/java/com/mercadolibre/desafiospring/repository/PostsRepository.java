package com.mercadolibre.desafiospring.repository;

import com.mercadolibre.desafiospring.exception.post.*;
import com.mercadolibre.desafiospring.model.*;
import com.mercadolibre.desafiospring.util.*;
import org.springframework.stereotype.Repository;
import java.text.ParseException;
import java.util.*;

@Repository
public class PostsRepository {
    private List<Post> posts;
    private List<Post> promoPosts;

    public PostsRepository() {
        this.posts = new ArrayList<>();
        this.promoPosts = new ArrayList<>();
    }

    public Post addPost(String date, int category,
                        double price,
                        String productName, String type, String brand,
                        String color, String notes) throws InvalidPostException {
        Post post = new Post(
                DateUtils.parseDate(date), category, price,
                new Product(productName, type, brand, color, notes));
        this.posts.add(post);
        return post;
    }

    public Post addPromoPost(String date, int category, double price,
                                  double discount, String productName,
                                  String type, String brand, String color,
                                  String notes) throws InvalidPostException {
        Post post = new Post(
                DateUtils.parseDate(date), category, price,
                new Product(productName, type, brand, color, notes), discount);
        this.promoPosts.add(post);
        return post;
    }
}
