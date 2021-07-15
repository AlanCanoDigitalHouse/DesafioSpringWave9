package com.example.demo.utils;

import com.example.demo.dtos.PostDto;
import com.example.demo.dtos.response.PostResponseDto;
import com.example.demo.dtos.response.PromoPostResponseDto;
import com.example.demo.dtos.response.SimplePostResponseDto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PostServiceUtils {

    public static List<PostResponseDto> parsePostToResponseDto(List<PostDto> list){
        List<PostResponseDto> responseList = new ArrayList<>();
        for (PostDto element : list){
            PostResponseDto elementResponse = createPostResponse(element);
            responseList.add(elementResponse);
        }
        return responseList;
    }

    private static PostResponseDto createPostResponse(PostDto element){
        if(element.isHasPromo()){
            return new PostResponseDto(element.getId_post() , element.getDate() , element.getDetail() ,
                    element.getCategory() , element.getPrice(), element.isHasPromo(),element.getDiscount());
        }
        return new SimplePostResponseDto(element.getId_post() , element.getDate() , element.getDetail() ,
                element.getCategory() , element.getPrice() , element.isHasPromo() , element.getDiscount());
    }

    public static void orderByDate(String order, List<PostDto> list){
        if (order.equals("date_asc"))
            list.sort(Comparator.comparing(PostDto::getDate));

        if (order.equals("date_desc"))
            list.sort(Comparator.comparing(PostDto::getDate).reversed());
    }

}
