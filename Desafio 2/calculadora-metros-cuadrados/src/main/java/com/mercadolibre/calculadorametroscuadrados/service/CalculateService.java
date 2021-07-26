package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.responses.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.responses.RoomResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CalculateService {

  public CalculateService(){
  }

  public Double calculateM2(RoomDTO room ){
    return room.getEnvironment_width()*room.getEnvironment_length();
  }

  public void propertyPrice(HouseDTO houseDTO, HouseResponseDTO houseResponseDTO){
    double result=0.0;
    for (RoomDTO room: houseDTO.getRooms()){
      result+=calculateM2(room)*houseDTO.getDistrict_price();
    }
    houseResponseDTO.setPrice(result);
  }

  public void propertySize(HouseDTO houseDTO,HouseResponseDTO houseResponseDTO){
    Double result=0.0;
    for (RoomDTO room: houseDTO.getRooms()){
      result+=calculateM2(room);
    }
    houseResponseDTO.setSquareFeet(result);
  }


  public void  biggestProperty(HouseDTO houseDTO,HouseResponseDTO houseResponseDTO){
    RoomDTO res = houseDTO.getRooms().get(0);

    for (RoomDTO room: houseDTO.getRooms()){
      if (calculateM2(res)<calculateM2(room)){res=room;}
    }
    houseResponseDTO.setBiggest(res);
  }


  public HouseResponseDTO mapResultado(HouseDTO houseDTO){

    HouseResponseDTO response = new HouseResponseDTO();
    response.setRooms(new ArrayList<>());
    //Parte 1
    propertySize(houseDTO,response);
    //Parte 2
    propertyPrice(houseDTO,response);
    //Parte 3
    biggestProperty(houseDTO,response);
    //Parte 4
    for ( RoomDTO room : houseDTO.getRooms() ){
      response.getRooms().add(new RoomResponseDTO(room.getEnvironment_name(),calculateM2(room)));
    }

    return response;
  }



}
