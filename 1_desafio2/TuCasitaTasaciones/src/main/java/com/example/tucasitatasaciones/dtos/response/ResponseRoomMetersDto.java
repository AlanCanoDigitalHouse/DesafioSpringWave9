package com.example.tucasitatasaciones.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ResponseRoomMetersDto {
    private Map<String,Double> roomsMeters;
}
