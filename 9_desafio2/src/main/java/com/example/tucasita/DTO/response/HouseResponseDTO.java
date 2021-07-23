package com.example.tucasita.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class HouseResponseDTO {

    private Map<String, Double> roomSizes;
    String maxSizeRoomName;
    private Double houseValue;
    private Double totalSquareM;
}
