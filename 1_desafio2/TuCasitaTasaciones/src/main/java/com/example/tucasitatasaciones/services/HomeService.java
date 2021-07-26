package com.example.tucasitatasaciones.services;

import com.example.tucasitatasaciones.dtos.DistrictDto;
import com.example.tucasitatasaciones.dtos.RoomDto;
import com.example.tucasitatasaciones.dtos.requests.HomeRequestDto;
import com.example.tucasitatasaciones.exceptions.GetDistrictException;
import com.example.tucasitatasaciones.repository.IDistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class HomeService implements IHomeService{

    @Autowired
    IDistrictRepository districtRepository;

    @Override
    public double calculateMeters(HomeRequestDto home){
        double t = 0;
        for (RoomDto r:home.getRooms()){
            t += (r.getWidth() * r.getLength());
        }
        return t;
    }

    @Override
    public double calculatePrice(HomeRequestDto home){
        double m = calculateMeters(home);
        DistrictDto district = districtRepository.findDistrict(home.getDistrict().getName(), home.getDistrict().getPrice());
        return m * home.getDistrict().getPrice();
    }

    @Override
    public RoomDto findBiggestRoom(HomeRequestDto home){
        double max = 0;
        RoomDto room = null;
        for (RoomDto r:home.getRooms()){
            if (r.getWidth() * r.getLength() > max){
                max = r.getWidth() * r.getLength();
                room = r;
            }
        }
        return room;
    }

    @Override
    public Map<String,Double> calculateMetersPerRoom(HomeRequestDto home){
        HashMap<String, Double> rooms = new HashMap<>();
        for (RoomDto r:home.getRooms()){
            rooms.put(r.getName(),r.getWidth() * r.getLength());
        }
        return rooms;
    }
}
