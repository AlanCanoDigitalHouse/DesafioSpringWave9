package com.mercado_libre.bootcamp.spring.desafio.utils;

import com.mercado_libre.bootcamp.spring.desafio.models.Post;

import java.util.Comparator;
import java.util.List;

public class PostUtils {

    public static void sortPostsByParam(List<Post> posts, String order) {

        if (order.equals("date_asc")) {
            posts.sort(Comparator.comparing(Post::getDate));
        } else {
            posts.sort(Comparator.comparing(Post::getDate).reversed());
        }
    }

    private PostUtils() {
        throw new IllegalStateException("Utility class");
    }

}
