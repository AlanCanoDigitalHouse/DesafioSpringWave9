package com.example.desafiospring.dtos.request;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Validated
public class PostRequestDto {
    @NotNull
    @Min(1)
    @Max(100)
    private Integer userId;
    @NotNull
    @Pattern(regexp="^([0-2][0-9]|(3)[0-1])(-)(((0)[0-9])|((1)[0-2]))(-)\\d{4}",message="date pattern dd-mm-yyyy")
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
