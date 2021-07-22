package com.api.firstspringchallenge.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@Validated
public class FollowersListRequestDTO {

    @NotNull(message = "userid cannot be null")
    private int userId;
    private String order;
}
