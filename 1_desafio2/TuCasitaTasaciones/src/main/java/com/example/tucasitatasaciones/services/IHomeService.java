package com.example.tucasitatasaciones.services;

import com.example.tucasitatasaciones.dtos.RoomDto;
import com.example.tucasitatasaciones.dtos.requests.HomeRequestDto;
import com.example.tucasitatasaciones.exceptions.GetDistrictException;

import java.util.Map;

public interface IHomeService {
    double calculateMeters(HomeRequestDto home);

    double calculatePrice(HomeRequestDto home);

    RoomDto findBiggestRoom(HomeRequestDto home);

    Map<String,Double> calculateMetersPerRoom(HomeRequestDto home);
}
