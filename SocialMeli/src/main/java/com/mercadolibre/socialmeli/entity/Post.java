package com.mercadolibre.socialmeli.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    int userId;
    int id_post;
    LocalDate createdDate;
    @NotNull(message = "detail cannot be null")
    Product detail;
    int category;
    double price;

    @JsonProperty("date")
    @JsonFormat(pattern="dd-MM-yyyy")
    public LocalDate getCreatedDate() {
        return createdDate;
    }
}
