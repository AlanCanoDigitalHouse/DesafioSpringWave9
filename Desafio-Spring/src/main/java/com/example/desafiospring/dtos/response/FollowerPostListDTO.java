package com.example.desafiospring.dtos.response;

import com.example.desafiospring.dtos.general.Publication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FollowerPostListDTO {
    private Integer userId;
    private List<Publication> post;
}
