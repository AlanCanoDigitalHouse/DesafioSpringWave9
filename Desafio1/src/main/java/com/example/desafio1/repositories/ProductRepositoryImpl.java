package com.example.desafio1.repositories;

import com.example.desafio1.dto.Post;
import com.example.desafio1.dto.User;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements IProductRepository{
    private static final List<Post> posts = initPosts();
    private static final AtomicInteger counter = initCounter();

    private static List<Post> initPosts(){
        return new ArrayList<>();
    }

    private static AtomicInteger initCounter(){
        return new AtomicInteger(0);
    }

    public void addNewPost(Post post){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-M-yyyy");
        LocalDate testDate = LocalDate.parse(post.getDate(), df);
        post.setPostId(counter.getAndIncrement());
        posts.add(post);
    }

    private boolean compareDates(String dateToSearch) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-M-yyyy");
        LocalDate d1 = LocalDate.parse(dateToSearch, df);
        LocalDate d2 = LocalDate.now().minusDays(14);
        return d1.isAfter(d2);
    }

    @Override
    public List<Post> listFollowedLastPosts(List<User> vendors) {
        List<Post> posts = new ArrayList<>();
        for(User user: vendors){
            posts.addAll(
                    ProductRepositoryImpl.posts.stream()
                            .filter(pe -> pe.getUserId().equals(user.getUserId())
                                    && compareDates(pe.getDate()))
                            .collect(Collectors.toList())
            );
        }

        return posts;
    }
}
