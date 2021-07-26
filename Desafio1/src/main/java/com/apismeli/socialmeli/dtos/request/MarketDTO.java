package com.apismeli.socialmeli.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class MarketDTO {
    private ArrayList<BuyerDTO> buyers;
    private ArrayList<SellerDTO> sellers;
}
