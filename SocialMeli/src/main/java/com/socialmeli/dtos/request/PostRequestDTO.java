package com.socialmeli.dtos.request;

import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Validated
public class PostRequestDTO {

    @NotNull(message = "User id is null value")
    private Integer userId;

    //private Integer id_post;
    @NotNull(message = "Date value is null value")
    private Date date;

    @NotNull(message = "Product is null value")
    @Valid
    private ProductRequestDTO detail;

    @NotNull(message = "Date value is null value")
    private Integer category;


    @NotNull(message = "Date value is null value")
    @Min(message = "Price value is less than zero", value = 0)
    private Float price;

}
