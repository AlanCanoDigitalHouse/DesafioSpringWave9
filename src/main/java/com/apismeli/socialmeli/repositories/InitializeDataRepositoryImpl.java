package com.apismeli.socialmeli.repositories;

import com.apismeli.socialmeli.dtos.request.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Repository
public class InitializeDataRepositoryImpl implements IInitializeDataRepository {

    public static MarketDTO marketDTO;

    public MarketDTO getRedDTO() {
        return marketDTO;
    }

    public void setRedDTO(MarketDTO marketDTO) {
        this.marketDTO = marketDTO;
    }

    @Override
    public MarketDTO initializeMarket() {
        marketDTO = new MarketDTO(initializeBuyers(), initializeSellers());
        return marketDTO;
    }

    @Override
    public ArrayList<BuyerDTO> initializeBuyers() {
        ArrayList<BuyerDTO> buyersInDB;
        buyersInDB = loadDataBaseBuyers();
        return buyersInDB;
    }

    @Override
    public ArrayList<SellerDTO> initializeSellers() {
        ArrayList<SellerDTO> sellersInDB;
        sellersInDB = loadDataBaseSellers();
        return sellersInDB;
    }


    private static ArrayList<BuyerDTO> loadDataBaseBuyers() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/Buyers.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mabObject(file);
    }

    private static ArrayList<BuyerDTO> mabObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<ArrayList<BuyerDTO>> typeReference = new TypeReference<>() {
        };
        ArrayList<BuyerDTO> buyerDTOS = null;

        try {
            buyerDTOS = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buyerDTOS;
    }


    private static ArrayList<SellerDTO> loadDataBaseSellers() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/Sellers.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mabObjectV(file);
    }


    private static ArrayList<SellerDTO> mabObjectV(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<ArrayList<SellerDTO>> typeReference = new TypeReference<>() {
        };
        ArrayList<SellerDTO> sellerDTOS = null;

        try {
            sellerDTOS = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sellerDTOS;

    }


}
