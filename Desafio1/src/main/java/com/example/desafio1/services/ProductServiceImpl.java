package com.example.desafio1.services;

import com.example.desafio1.dto.Post;
import com.example.desafio1.dto.User;
import com.example.desafio1.repositories.IProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ProductServiceImpl implements IProductService{
    private final IProductRepository productRepository;

    public ProductServiceImpl(IProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public void addNewPost(Post post) {
        productRepository.addNewPost(post);
    }

    @Override
    public List<Post> listLastFollowedPosts(List<User> vendors, String order) {
        List<Post> posts =  productRepository.listFollowedLastPosts(vendors);
        sortBy(posts, order);
        return posts;
    }

    private void sortBy(List<Post> posts, String order){
        if(Objects.isNull(order)) return;
        switch (order.toLowerCase(Locale.ROOT)){
            case "date_asc":
                posts.sort((p1, p2) -> compareDates(p1.getDate(), p2.getDate()));
                break;
            case "date_desc":
                posts.sort((p1, p2) -> compareDates(p2.getDate(), p1.getDate()));
                break;
            default:
                break;
        }
    }

    private Integer compareDates(String d1, String d2) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-M-yyyy");
        LocalDate firstDate = LocalDate.parse(d1, df);
        LocalDate secondDate = LocalDate.parse(d2,df);
        return firstDate.compareTo(secondDate);
    }
}
