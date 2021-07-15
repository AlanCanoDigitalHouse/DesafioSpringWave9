package com.example.demo.Handlers;

import com.example.demo.Models.PostModel;

import java.util.Comparator;
import java.util.List;

public class SortByDate {

    public static List<PostModel> sortByDate(List<PostModel> posts, String order) {
        if( order.equals("date_desc")) {
            posts.sort((p1, p2) -> p2.getDate().compareTo(p1.getDate()));
        }
        else if(order.equals("date_asc")) {
            posts.sort(Comparator.comparing(PostModel::getDate));
        }
        return posts;
    }

}
