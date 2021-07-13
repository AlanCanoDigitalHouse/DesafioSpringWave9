package com.example.desafiospring.DTOS.requests;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Validated
public class FollowUserRequestDTO {
    @NotNull(message = "followerUserId is mandatory")
    private Integer followerUserId;
    @NotNull(message = "followedUserId is mandatory")
    private Integer followedUserId;
}
