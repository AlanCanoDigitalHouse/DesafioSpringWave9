package com.example.demo.Handler;

import com.example.demo.Entities.Post;
import com.example.demo.Entities.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UserSorter {

    public List<User> userSorterByName (List<User> list,String ordType) {
        List<User> sortedUsers = new ArrayList<>();
        if (ordType.compareTo("name_asc") == 0) {

            sortedUsers = list.stream()
                    .sorted(Comparator.comparing(User::getUserName))
                    .collect(Collectors.toList());
        }
        if (ordType.compareTo("name_desc") == 0) {

            sortedUsers = list.stream()
                    .sorted(Comparator.comparing(User::getUserName).reversed())
                    .collect(Collectors.toList());
        }
        return sortedUsers;

    }

}
