package com.example.desafiospring.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserPostDto {

    private Long userId;
    private String userName;
    private Long promoproducts_count;
    private List<PostDto> posts;

}
