package com.example.demo.DTOs;

import com.example.demo.Models.PostModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostsDTO {

    private int userId;
    private String userName;
    private List<PostModel> posts;

}
