package com.example.socialmeli.dtos.responses;

import com.example.socialmeli.dtos.requests.RequestProductDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ResponsePostDto {
    private Date date;
    private RequestProductDto detail;
    private Integer category;
    private double price;
}
