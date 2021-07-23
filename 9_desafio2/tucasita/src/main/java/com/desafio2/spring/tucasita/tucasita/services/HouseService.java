package com.desafio2.spring.tucasita.tucasita.services;

import com.desafio2.spring.tucasita.tucasita.dtos.request.HouseDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.request.DistrictDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.request.RoomDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.response.HouseBigRoomDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.response.HouseRoomsDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.response.HouseSizeDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.response.HouseValueDTO;
import com.desafio2.spring.tucasita.tucasita.exceptions.DistrictDoesNotExistException;
import com.desafio2.spring.tucasita.tucasita.exceptions.ServiceExceptions;
import com.desafio2.spring.tucasita.tucasita.exceptions.WrongPriceException;
import com.desafio2.spring.tucasita.tucasita.repositories.PriceLocationRepository;
import org.springframework.stereotype.Service;

@Service
public class HouseService implements IHouseService{

    PriceLocationRepository repository;

    public HouseService(PriceLocationRepository repository){
        this.repository = repository;
    }

    @Override
    public HouseSizeDTO getHouseSize(HouseDTO house) throws ServiceExceptions {
        validateDistrict(house);
        double result = 0;
        for (RoomDTO room: house.getRooms()){
            result += getMts(room);
        }
        return new HouseSizeDTO(result);
    }

    private void validateDistrict(HouseDTO house) throws ServiceExceptions {
        DistrictDTO districtDTO = house.getDistrict();
        DistrictDTO districtDAO = repository.findPriceLocation(districtDTO.getDistrict_name());
        if(districtDAO == null) {
            throw new DistrictDoesNotExistException(districtDTO.getDistrict_name());
        }
        if (districtDAO.getDistrict_price() != districtDTO.getDistrict_price()){
            throw new WrongPriceException();
        }
    }

    @Override
    public HouseValueDTO getHouseValue(HouseDTO house) throws ServiceExceptions {
        return null;
    }

    @Override
    public HouseBigRoomDTO getHouseBigRoom(HouseDTO house) throws ServiceExceptions {
        return null;
    }

    @Override
    public HouseRoomsDTO getHouseRooms(HouseDTO house) throws ServiceExceptions {
        return null;
    }

    public double getMts(RoomDTO room){
        return room.getEnvironment_length() * room.getEnvironment_width();
    }
}
