package com.example.desafiospring.DTOS.requests;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class UserIDAndOrderRequestDTO {
    @NotNull(message = "userId is mandatory")
    private Integer userId;
    private String order;
}
