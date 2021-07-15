package com.example.demo.Handler;

import com.example.demo.Entities.Post;
import com.example.demo.Entities.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PostSorter {


    public List<Post> OrderByDate (List <Post> list, String ordType) {
        List<Post> sortedUsers = new ArrayList<>();
        if (ordType.compareTo("date_asc") == 0) {

            sortedUsers = list.stream()
                    .sorted(Comparator.comparing(Post::getDate))
                    .collect(Collectors.toList());
        }
        if (ordType.compareTo("date_desc") == 0) {

            sortedUsers = list.stream()
                    .sorted(Comparator.comparing(Post::getDate).reversed())
                    .collect(Collectors.toList());
        }
        return sortedUsers;
    }
}


