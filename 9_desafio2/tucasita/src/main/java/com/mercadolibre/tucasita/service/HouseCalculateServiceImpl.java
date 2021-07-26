package com.mercadolibre.tucasita.service;

import com.mercadolibre.tucasita.domain.District;
import com.mercadolibre.tucasita.domain.House;
import com.mercadolibre.tucasita.domain.Room;
import com.mercadolibre.tucasita.dto.RoomDTO;
import com.mercadolibre.tucasita.repository.DistrictRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HouseCalculateServiceImpl implements HouseCalculateService {

    private DistrictRepository districtRepository;

    public HouseCalculateServiceImpl(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Override
    public double calculateTotalSquareMeters(House house) {
        return house.getRooms()
                .stream()
                .mapToDouble(r -> r.getLength() * r.getWidth())
                .sum();
    }

    @Override
    public double calculateHousePrice(House house) {

        District district = this.districtRepository.findByName(house.getDistric().getName());

        if(district == null || district.getSquareMeterPrice() != house.getDistric().getSquareMeterPrice())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid district input.");

        return this.calculateTotalSquareMeters(house) * house.getDistric().getSquareMeterPrice();
    }

    @Override
    public RoomDTO findBiggestRoom(House house) {

        double max = Double.MIN_VALUE;
        Room biggestRoom = null;

        for(Room r : house.getRooms()) {

            double roomSize = r.getLength() * r.getWidth();

            if(roomSize > max) {
                max = roomSize;
                biggestRoom = r;
            }
        }

        return new RoomDTO(biggestRoom.getName(), max);
    }

    @Override
    public List<RoomDTO> calculateRoomSizes(House house) {
       return house.getRooms()
               .stream()
               .map(r -> new RoomDTO(r.getName(), r.getWidth() * r.getLength()))
               .collect(Collectors.toList());
    }
}
