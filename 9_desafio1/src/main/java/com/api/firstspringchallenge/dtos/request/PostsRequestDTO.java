package com.api.firstspringchallenge.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class PostsRequestDTO {
    @NotNull(message = "userid cannot be null")
    private int userId;
    private String order;
}
