package com.example.desafiospring.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class PostDTO {

    @NotNull
    private Integer postID;

    @NotNull(message = "user id is null")
    private Integer userID;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @NotNull(message = "publicationDate is null")
    private LocalDate publicationDate;

    @NotNull
    private DetailPostDTO detail;

    @NotNull
    private Integer category;

    @Min(value= 0)
    @NotNull
    private Double price;

}
