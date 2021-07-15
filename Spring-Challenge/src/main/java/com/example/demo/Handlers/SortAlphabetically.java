package com.example.demo.Handlers;

import com.example.demo.Models.UserModel;

import java.util.Comparator;
import java.util.List;

public class SortAlphabetically {

    public static List<UserModel> SortAlphabetically(List<UserModel> users, String order) {
        if(order.equals("name_desc")) {
            users.sort((f1, f2) -> f2.getUserName().compareTo(f1.getUserName()));
        } else if(order.equals("name_asc")) {
            users.sort(Comparator.comparing(UserModel::getUserName));
        }
        return users;
    }

}
