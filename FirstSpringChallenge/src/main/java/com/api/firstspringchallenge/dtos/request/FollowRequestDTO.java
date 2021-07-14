package com.api.firstspringchallenge.dtos.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@Getter
@Setter
public class FollowRequestDTO {

    @NotNull(message = "userid cannot be null")
    private int userId;

    @NotNull(message = "username cannot be null")
    private int userIdToFollow;

}
