package com.example.DesafioSpring.dtos;

import com.example.DesafioSpring.models.Seller;
import lombok.Data;

import java.util.List;

@Data

public class BuyerResponseDTO {

    private Integer userId;
    private String userName;
    private List<Seller> followed;

}
