package com.example.demo.repositories;

import com.example.demo.dtos.generals.PriceDto;
import com.example.demo.dtos.request.RoomRequestDto;
import com.example.demo.exceptions.DistrictNotFound;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

@Repository
public class HouseRepository implements HouseRepositoryImpl{

    private static DecimalFormat df = new DecimalFormat("0.00");
    private List<PriceDto> districts;
    private String SCOPE;


    public HouseRepository() {
        Properties properties =  new Properties();
        try {
            properties.load(new ClassPathResource("application.properties").getInputStream());
            this.SCOPE = properties.getProperty("api.scope");
            this.loadDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RoomRequestDto biggestRoom(List<RoomRequestDto> rooms) {
        /*TODO: Logic to get biggest room*/
        rooms.sort(
                Comparator.comparing(RoomRequestDto::getArea).reversed()
        );
        return rooms.get(0);
    }

    @Override
    public List<RoomRequestDto> roomsArea(List<RoomRequestDto> rooms) {
        return rooms;
    }

    @Override
    public PriceDto searchDistrict(String environment_name) throws DistrictNotFound {
        districts = loadDatabase();
        PriceDto result = null;
        if(Objects.nonNull(districts)){
            var item = districts.stream().filter(house -> house.getDistrict_name().equals(environment_name)).findFirst();
            if (item.isPresent()){
                result = item.get();
            } else {
                throw new DistrictNotFound();
            }
        }
        return result;
    }

    @Override
    public Double getHouseSize(List<RoomRequestDto> rooms) {
        /*TODO: Logic to get house´s size*/
        Double houseSize = 0.0;
        for (RoomRequestDto room : rooms) {
            houseSize += room.getEnvironment_length()*room.getEnvironment_width();
        }
        return Double.valueOf(df.format(houseSize));
    }

    public List<PriceDto> loadDatabase() {
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:static/price.json");

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return mapObject(file);
    }

    private List<PriceDto> mapObject(File file){
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<PriceDto>> typeReference = new TypeReference<>(){};
        List<PriceDto> priceDTOS = null;
        try {
            priceDTOS = objectMapper.readValue(file, typeReference);
        }catch (IOException e){
            e.printStackTrace();
        }
        return priceDTOS;
    }
}
