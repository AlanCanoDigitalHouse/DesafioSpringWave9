package com.example.demo.DTOs;

import com.example.demo.Models.PostModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FollowedPostsDTO {

    private int userId;
    private List<PostModel> posts;

}
