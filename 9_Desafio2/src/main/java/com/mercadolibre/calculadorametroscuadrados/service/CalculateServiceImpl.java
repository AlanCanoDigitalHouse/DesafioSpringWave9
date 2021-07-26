package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.ResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.PriceNotValidException;
import com.mercadolibre.calculadorametroscuadrados.repository.DistrictRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CalculateServiceImpl implements CalculateService {

    private DistrictRepository districtRepository;

    public CalculateServiceImpl(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Override
    public ResponseDTO calculate(HouseDTO house) {
        validateInput(house);
        ResponseDTO response = new ResponseDTO();
        response.setName(house.getProp_name());
        response.setEnvironments(calculateSqmPerRoom(house));
        response.setSqm(calculateTotalSqm(response));
        response.setPrice(calculatePrice(response, house));
        response.setBiggest(findBiggestRoom(response));
        return response;
    }

    private void validateInput(HouseDTO houseDTO){
        double dbPrice=districtRepository.getPriceByDistrict(houseDTO.getDistrict_name());
        if(dbPrice!= houseDTO.getDistrict_price()){
            throw new PriceNotValidException();
        }
    }

    private Map.Entry<String, Double> findBiggestRoom(ResponseDTO response) {
       return response.getEnvironments().entrySet()
               .stream()
               .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
               .findFirst().orElse(null);
       }

    private double calculateTotalSqm(ResponseDTO response) {
        return response.getEnvironments().entrySet().stream().map(Map.Entry::getValue).reduce(Double::sum).orElse(0d);
    }

    private Map<String, Double> calculateSqmPerRoom(HouseDTO request) {
        return request.getEnvironments().stream()
                .collect(Collectors.toMap(roomDTO -> roomDTO.getEnvironment_name(), roomDTO -> roomDTO.getEnvironment_length() * roomDTO.getEnvironment_width()));
    }

    private double calculatePrice(ResponseDTO responseDTO, HouseDTO houseDTO) {
        return responseDTO.getSqm() * houseDTO.getDistrict_price();
    }


}
