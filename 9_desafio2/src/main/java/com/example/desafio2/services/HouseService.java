package com.example.desafio2.services;

import com.example.desafio2.dtos.*;
import com.example.desafio2.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class HouseService {

    public  HouseDetailResponseDto getPrice(HouseDTO house) {
        var squareMeters = sumEveryEnvMeters(house.getEnvironments());
        var price = calculatePrice(squareMeters,house.getDistrict_price());
        List<EnvResponseDto> listEnvs = getPriceForEnvs(house.getEnvironments(),house.getDistrict_price());
        HouseDetailResponseDto HouseResponse = new HouseDetailResponseDto(house.getProp_name(),
                price,house.getDistrict_name(),
                house.getDistrict_price(),listEnvs);
        return HouseResponse;
    }

    public HouseResponseDTO getSquareMeters(HouseDTO house) {
        double meters = sumEveryEnvMeters(house.getEnvironments());
        return  new HouseResponseDTO(house.getProp_name(),meters);
    }

    public EnvDTO getBiggerEnv(HouseDTO house) {
        return getBiggerEnv(house.getEnvironments());
    }

    public List<EnvResponseDto> getListEnv(HouseDTO house) {
        return getPriceForEnvs(house.getEnvironments(),house.getDistrict_price());
    }

    private double calculatePrice(double totalSquareMeters, double districtPrice){
        return totalSquareMeters*districtPrice;
    }

    private double sumEveryEnvMeters(List<EnvDTO> envs){
        double total = 0;
        for (EnvDTO env:envs) {
            total+=env.getEnvironment_length()*env.getEnvironment_width();
        }
        return total;
    }

    private Double getSquareMeters(double length,double width){
        return length*width;
    }

    private EnvDTO getBiggerEnv(List<EnvDTO> envs){
        var envDto = envs.stream()
                .max(Comparator.comparing(o -> getSquareMeters(o.getEnvironment_length(),
                        o.getEnvironment_width())));
        if (envDto.isPresent()){
            return envDto.get();
        }else{
            throw new BadRequestException("Not enough envs to compare");
        }
    }

    private List<EnvResponseDto> getPriceForEnvs(List<EnvDTO> envs,double districtPrice){
        List<EnvResponseDto> list = new ArrayList<>();
        for (EnvDTO env: envs) {
            var squareMeters = getSquareMeters(env.getEnvironment_length(),env.getEnvironment_width());
            var price = squareMeters*districtPrice;
            list.add(new EnvResponseDto(env.getEnvironment_name(),squareMeters,price));
        }
        return list;
    }
}
