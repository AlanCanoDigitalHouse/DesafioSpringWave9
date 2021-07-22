package com.mercadolibre.desafiotesting.dto;


import lombok.Data;

@Data
public class HouseResponseDto extends HouseDto {

    private Double squareFeet;
    private Double price;
    private RoomDto biggest;


    public HouseResponseDto(HouseDto house) {
        this.setProp_name(house.getProp_name());
        this.setDistrict_name(house.getDistrict_name());
        this.setRooms(house.getRooms());
    }
}
