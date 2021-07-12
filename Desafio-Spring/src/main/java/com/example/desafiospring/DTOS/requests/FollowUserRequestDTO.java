package com.example.desafiospring.DTOS.requests;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Validated
public class FollowUserRequestDTO {
    @NotNull(message = "userId is mandatory")
    private Integer userId;
    @NotNull(message = "userIdToFollow is mandatory")
    private Integer userIdToFollow;
}
