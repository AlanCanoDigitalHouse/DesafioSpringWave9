package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dto.*;
import com.mercadolibre.calculadorametroscuadrados.exceptions.DistrictNotFoundException;
import com.mercadolibre.calculadorametroscuadrados.repositories.DistrictsRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor

@Service
public class HouseService implements IHouseService{

    DistrictsRepository districtsRepository;

    @Autowired
    public HouseService(DistrictsRepository districtsRepository) {
        this.districtsRepository = districtsRepository;
    }

    @Override
    public HouseResponseDTO calculate(HouseTotalSizeRequestDTO houseDTO) {
        List<RoomDTO> rooms = houseDTO.getRooms();
        Double result = 0D;
        if (rooms.size()>0){
            for ( RoomDTO r : rooms) {
                result += r.getEnvironment_width() * r.getEnvironment_length();
            }
        }
        HouseResponseDTO houseResponseDTO = new HouseResponseDTO();
        houseResponseDTO.setArea(result);
        houseResponseDTO.setProp_name(houseDTO.getProp_name());
        return houseResponseDTO;
    }

    @Override
    public HousePriceResponseDTO totalPrice(HousePriceRequestDTO houseDTO) {
        HouseTotalSizeRequestDTO houseTotalSizeRequestDTO = new HouseTotalSizeRequestDTO();
        houseTotalSizeRequestDTO.setRooms(houseDTO.getRooms());
        HouseResponseDTO houseResponseDTO = calculate(houseTotalSizeRequestDTO);
        Double resultAreaPrice = 0D;
        var district_price = districtsRepository.getDistrict(houseDTO.getDistrict_name());
        if (district_price) {
            resultAreaPrice = houseDTO.getDistrict_price() * houseResponseDTO.getArea().doubleValue();
        }else{
            throw new DistrictNotFoundException("/// El barrio no existe.");
        }
        HousePriceResponseDTO housePriceResponseDTO = new HousePriceResponseDTO();
        housePriceResponseDTO.setProp_name(houseDTO.getProp_name());
        housePriceResponseDTO.setPrice(resultAreaPrice);
        return housePriceResponseDTO;
    }

    @Override
    public RoomResponseBiggestDTO biggestRoom(HouseDTO houseDTO) {
        List<RoomDTO> rooms = houseDTO.getRooms();
        Double biggest_size = 0D;
        String biggest_name = "";
        if (rooms.size()>0){
            for (RoomDTO r : rooms){
                var aux = r.getEnvironment_length() * r.getEnvironment_width();
                if (aux>biggest_size){
                    biggest_name = r.getEnvironment_name();
                    biggest_size = aux;
                }
            }
        }
        return new RoomResponseBiggestDTO(biggest_name, biggest_size);
    }

    @Override
    public HouseSizesResponseDTO countSizes(HouseDTO house) {
        List<RoomDTO> rooms = house.getRooms();
        List<RoomCountResponseDTO> roomCountResponseDTOS = new ArrayList<>();
        if (rooms.size()>0){
            for ( RoomDTO r : rooms) {
                Double result = r.getEnvironment_width() * r.getEnvironment_length();
                RoomCountResponseDTO dto = new RoomCountResponseDTO();
                dto.setSize(result);
                dto.setEnvironment_name(r.getEnvironment_name());
                roomCountResponseDTOS.add(dto);
            }
        }
        HouseSizesResponseDTO houseSizesResponseDTO = new HouseSizesResponseDTO();
        houseSizesResponseDTO.setProp_name(house.getProp_name());
        houseSizesResponseDTO.setEnvironment_sizes(roomCountResponseDTOS);
        return houseSizesResponseDTO;
    }

    @Override
    public String start() {

        districtsRepository.loadDistricts();
        return "Barrios cargados: Centro, Chacarita, Valle Chico, Villa Reyes y Terrazas del Valle.";
    }


}
