package com.example._9desafio2.services;
import com.example._9desafio2.dtos.request.PropertyDTO;
import com.example._9desafio2.dtos.response.EnviromentResponseDTO;
import com.example._9desafio2.dtos.response.PropertyResponseDTO;
import com.example._9desafio2.dtos.request.DistrictDTO;
import com.example._9desafio2.dtos.request.EnviromentDTO;
import com.example._9desafio2.exceptions.DistrictNotFoundException;
import com.example._9desafio2.exceptions.PriceNotMatchException;
import com.example._9desafio2.repositories.IPriceRepository;
import com.example._9desafio2.repositories.PriceRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalculateService implements ICalculateService {

    IPriceRepository priceRepository;

    public CalculateService(PriceRepository priceRepository){
        this.priceRepository = priceRepository;
    }

    public PropertyResponseDTO calculate(PropertyDTO house) throws PriceNotMatchException, DistrictNotFoundException {
        PropertyResponseDTO response = new PropertyResponseDTO(house);
        calculateRoomSquareFeet(house, response);
        response.setPrice(calculatePrice(response.getSquareMeters(),house.getDistrict()));
        return response;
    }

    private void calculateRoomSquareFeet(PropertyDTO house, PropertyResponseDTO response) {
        Double totalSquareFeet = 0.0;
        EnviromentDTO biggest = null;
        Double maxRoom = 0.0;
        List<EnviromentResponseDTO> enviromentDTOList=new ArrayList<>();
        for (EnviromentDTO room : house.getEnvironments()) {
            Double squareFeet = room.getSquareFeet();
            enviromentDTOList.add(new EnviromentResponseDTO(room.getEnvironment_name(),squareFeet));
            totalSquareFeet += squareFeet;
            if (biggest == null || squareFeet > maxRoom){
                biggest = room;
                maxRoom = squareFeet;
            }
        }
        response.setSquareMeters(totalSquareFeet);
        response.setBiggest(biggest);
        response.setEnviroments(enviromentDTOList);
    }

    //si el precio que traigo matchea con el del repo lo usa, sino lanza la excepcion
    private Double calculatePrice(Double squareFeet, DistrictDTO district) throws PriceNotMatchException, DistrictNotFoundException {
        Double price;
       if((priceRepository.findPriceDistrict(district.getDistrict_name())).getDistrict_price().equals(district.getDistrict_price()) ){
           price= squareFeet * district.getDistrict_price();
       }else {
           throw new PriceNotMatchException("El precio encontrado no coincide con el precio ingresado");
       }
       return price;
    }


}
