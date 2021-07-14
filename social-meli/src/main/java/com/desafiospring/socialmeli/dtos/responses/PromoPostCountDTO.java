package com.desafiospring.socialmeli.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromoPostCountDTO {

    private int userId;
    private String userName;
    private int promoproducts_count;

}
