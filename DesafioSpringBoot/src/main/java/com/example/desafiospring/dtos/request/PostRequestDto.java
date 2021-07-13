package com.example.desafiospring.dtos.request;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Validated
public class PostRequestDto {
    @NotNull
    @Min(1)
    @Max(100)
    private Integer userId;
    @NotNull
    private String date;
    @NotNull
    @Valid
    private ProductRequestDto detail;
    @NotNull
    @Min(1)
    @Max(100)
    private Integer category;
    @NotNull
    private Double price;
}
