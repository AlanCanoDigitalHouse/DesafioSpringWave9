package com.apismeli.socialmeli.repositories;

import com.apismeli.socialmeli.dtos.request.BuyerDTO;
import com.apismeli.socialmeli.dtos.request.MarketDTO;
import com.apismeli.socialmeli.dtos.request.SellerDTO;

import java.util.ArrayList;

public interface IInitializeDataRepository {
    MarketDTO initializeMarket();

    ArrayList<BuyerDTO> initializeBuyers();

    ArrayList<SellerDTO> initializeSellers();
}
