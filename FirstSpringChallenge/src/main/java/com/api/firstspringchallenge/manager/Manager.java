package com.api.firstspringchallenge.manager;

import com.api.firstspringchallenge.models.Post;
import com.api.firstspringchallenge.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Manager {

    public static List<User> orderUserBy(String order, List<User> users) {
        if (order != null) {
            Comparator<User> comparator = Comparator.comparing(User::getUsername);
            if ("name_desc".equals(order)) {
                return users.stream().sorted(comparator.reversed()).collect(Collectors.toList());
            }
            return users.stream().sorted(comparator).collect(Collectors.toList());
        }
        return users;
    }

    public static List<Post> orderPostsBy(String order, List<Post> posts) {
        Comparator<Post> comparator = Comparator.comparing(Post::getDate);
        if ("date_desc".equals(order)) {
            return posts.stream().sorted(comparator.reversed()).collect(Collectors.toList());
        }
        return posts.stream().sorted(comparator).collect(Collectors.toList());
    }

}
