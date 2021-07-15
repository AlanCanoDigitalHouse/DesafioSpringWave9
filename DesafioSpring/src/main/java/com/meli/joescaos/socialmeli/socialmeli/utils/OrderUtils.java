package com.meli.joescaos.socialmeli.socialmeli.utils;

import com.meli.joescaos.socialmeli.socialmeli.dtos.responses.BuyerDto;
import com.meli.joescaos.socialmeli.socialmeli.dtos.responses.PostsListDto;
import com.meli.joescaos.socialmeli.socialmeli.dtos.responses.SellerDto;
import com.meli.joescaos.socialmeli.socialmeli.models.Post;

import java.util.*;
import java.util.stream.Collectors;


public class OrderUtils {

    private static final String NAME_ASC = "name_asc";
    private static final String NAME_DESC = "name_desc";
    private static final String Date_ASC = "date_asc";
    private static final String Date_DESC = "date_desc";

    public static void orderFollowers(List<BuyerDto> buyers, String order) {
        if (NAME_DESC.equals(order))
            Collections.sort(buyers, Comparator.comparing(BuyerDto::getUserName).reversed());
        if (NAME_ASC.equals(order))
            Collections.sort(buyers, Comparator.comparing(BuyerDto::getUserName));
    }

    public static void orderFollowed(List<SellerDto> sellers, String order) {
        if (NAME_DESC.equals(order))
            Collections.sort(sellers, Comparator.comparing(SellerDto::getUserName).reversed());
        if (NAME_ASC.equals(order))
            Collections.sort(sellers, Comparator.comparing(SellerDto::getUserName));
    }

    public static void orderDates(List<Post> postsList, String order) {
        if (Date_DESC.equals(order))
            postsList.sort(Comparator.comparing(Post::getDate).reversed());
        if (Date_ASC.equals(order))
            postsList.sort(Comparator.comparing(Post::getDate));
    }

}
