package com.meli.joescaos.desafiotesting.utilsTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.joescaos.desafiotesting.dto.HouseDTO;
import com.meli.joescaos.desafiotesting.dto.HouseResponseDTO;
import com.meli.joescaos.desafiotesting.dto.PriceDTO;
import com.meli.joescaos.desafiotesting.dto.RoomDTO;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestUtilsGenerator {

    private static List<PriceDTO> priceDTOList;

    public static List<PriceDTO> readFile(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            priceDTOList = Arrays.asList(mapper.readValue(new ClassPathResource("/static/price.json").getFile(), PriceDTO[].class));
        } catch (IOException e){
            e.printStackTrace();
            priceDTOList = new ArrayList<>();
        }
        return priceDTOList;
    }

    public static List<PriceDTO> readWrongFile(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            priceDTOList = Arrays.asList(mapper.readValue(new ClassPathResource("/static/pric.json").getFile(), PriceDTO[].class));
        } catch (IOException e){
            e.printStackTrace();
            priceDTOList = new ArrayList<>();
        }
        return priceDTOList;
    }

    public static HouseDTO createHouse(){
        List<RoomDTO> roomsList = new ArrayList<>();
        roomsList.add(new RoomDTO("Presidential", 15, 10));
        roomsList.add(new RoomDTO("Briggs", 18, 12));
        HouseDTO house = new HouseDTO();
        house.setProp_name("Presidential");
        house.setDistrict_name("Palermo");
        house.setDistrict_price(1000);
        house.setRooms(roomsList);
        return house;
    }

    public static HouseDTO createHouseWithWrongName(){
        List<RoomDTO> roomsList = new ArrayList<>();
        roomsList.add(new RoomDTO("Presidential", 15, 10));
        roomsList.add(new RoomDTO("Briggs", 18, 12));
        HouseDTO house = new HouseDTO();
        house.setProp_name("Presidential");
        house.setDistrict_name("Alavez");
        house.setDistrict_price(1000);
        house.setRooms(roomsList);
        return house;
    }

    public static PriceDTO createPriceDTO(){
        return new PriceDTO("Palermo", 1000);
    }

    public static HouseResponseDTO createHouseResponse(){
        List<RoomDTO> roomsList = new ArrayList<>();
        roomsList.add(new RoomDTO("Presidential", 15, 10));
        roomsList.add(new RoomDTO("Briggs", 18, 12));
        HouseResponseDTO house = new HouseResponseDTO();
        house.setProp_name("Presidential");
        house.setDistrict_name("Palermo");
        house.setDistrict_price(1000);
        house.setRooms(roomsList);
        house.setSquareFeet(366);
        house.setPrice(366000.0);
        house.setBiggest(new RoomDTO("Briggs", 18, 12));
        return house;
    }

}
