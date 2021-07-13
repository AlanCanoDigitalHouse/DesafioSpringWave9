package com.example.socialmeli.dtos.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@Data
public class ResponsePostsListDto {
    private Integer userId;
    private ArrayList<ResponsePostDto> posts;
}
