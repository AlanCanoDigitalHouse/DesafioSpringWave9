package com.socialmeli.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Validated
public class PostRequestDTO {

    @NotNull(message = "User id is null value")
    private Integer userId;

    //private Integer id_post;
    @NotNull(message = "Date value is null value")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    @NotNull(message = "Product is null value")
    @Valid
    private ProductRequestDTO detail;

    @NotNull(message = "Date value is null value")
    private Integer category;


    @NotNull(message = "Date value is null value")
    @Min(message = "Price value is less than zero", value = 0)
    private Double price;

}
