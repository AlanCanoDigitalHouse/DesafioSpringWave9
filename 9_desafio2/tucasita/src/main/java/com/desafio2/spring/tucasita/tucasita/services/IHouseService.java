package com.desafio2.spring.tucasita.tucasita.services;

import com.desafio2.spring.tucasita.tucasita.dtos.request.HouseDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.response.HouseBigRoomDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.response.HouseRoomsDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.response.HouseSizeDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.response.HouseValueDTO;
import com.desafio2.spring.tucasita.tucasita.exceptions.ServiceExceptions;

public interface IHouseService {
    
    HouseSizeDTO getHouseSize(HouseDTO house) throws ServiceExceptions;

    HouseValueDTO getHouseValue(HouseDTO house) throws ServiceExceptions;

    HouseBigRoomDTO getHouseBigRoom(HouseDTO house) throws ServiceExceptions;

    HouseRoomsDTO getHouseRooms(HouseDTO house) throws ServiceExceptions;
}
