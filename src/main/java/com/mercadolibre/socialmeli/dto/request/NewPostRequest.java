package com.mercadolibre.socialmeli.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Date;
@Validated
@Data
@Builder
@AllArgsConstructor
public class NewPostRequest {
    @NotNull(message = "userId cannot be null")
    private Integer userId;
    @NotNull(message = "invalid date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;
    @NotNull(message = "invalid detail")
    private ProductRequest detail;
    @NotNull(message = "invalid category")
    private Integer category;
    @NotNull(message = "invalid price")
    private Double price;
}
