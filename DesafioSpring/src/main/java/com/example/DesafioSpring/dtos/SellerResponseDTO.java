package com.example.DesafioSpring.dtos;

import com.example.DesafioSpring.models.Buyer;
import lombok.Data;

import java.util.List;

@Data

public class SellerResponseDTO {

    private Integer userid;
    private String userName;
    private Integer followers_count;
    private List<Buyer> followers;

}
