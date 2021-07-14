package com.mercadolibre.desafio_spring.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    int userId;
    int id_post;
    @JsonFormat(pattern="dd-MM-yyyy")
    Date date;
    Product detail;
    int category;
    double price;
}
