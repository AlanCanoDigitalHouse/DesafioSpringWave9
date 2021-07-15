package com.example.desafio1springboot.dtos.responseDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPostsResposeDTO<T> {

    private Integer userId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userName;

    private List<T> posts;

    public UserPostsResposeDTO(Integer userId) {
        this.userId = userId;
    }
}
