package com.example.desafiospring.DTOS.requests;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class OnlyUserIDRequestDTO {
    @NotNull(message = "userId is mandatory")
    private Integer userId;
}
