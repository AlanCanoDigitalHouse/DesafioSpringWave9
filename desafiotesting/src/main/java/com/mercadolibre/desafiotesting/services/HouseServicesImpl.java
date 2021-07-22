package com.mercadolibre.desafiotesting.services;

import com.mercadolibre.desafiotesting.dto.HouseDto;
import com.mercadolibre.desafiotesting.dto.HouseResponseDto;
import com.mercadolibre.desafiotesting.dto.RequestHouseDto;
import com.mercadolibre.desafiotesting.dto.RoomDto;
import com.mercadolibre.desafiotesting.exceptions.HouseException;
import org.springframework.stereotype.Service;

@Service
public class HouseServicesImpl implements HouseService{
    @Override
    public HouseResponseDto calculate(RequestHouseDto house) throws HouseException {
        System.out.println(house.getHouseDto().getProp_name());
        HouseResponseDto response = new HouseResponseDto(house.getHouseDto());
        calculateRoomSquareFeet(house, response);
        response.setPrice(calculatePrice(response.getSquareFeet(),house.getDistrict_price()));
        return response;
    }

    private void calculateRoomSquareFeet(RequestHouseDto requestHouseDto, HouseResponseDto response) {
        Double totalSquareFeet = 0.0;
        RoomDto biggest = null;
        Double maxRoom = 0.0;
        HouseDto house = requestHouseDto.getHouseDto();
        for (RoomDto room : house.getRooms()) {
            Double squareFeet = room.getSquareFeet();
            totalSquareFeet += squareFeet;
            if (biggest == null || squareFeet > maxRoom){
                biggest = room;
                maxRoom = squareFeet;
            }
        }
        response.setSquareFeet(totalSquareFeet);
        response.setBiggest(biggest);
    }

    private Double calculatePrice(Double result,Double meterPrice) throws HouseException {

        return result * meterPrice;
    }
}

