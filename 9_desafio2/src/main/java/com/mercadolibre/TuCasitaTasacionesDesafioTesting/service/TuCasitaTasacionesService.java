package com.mercadolibre.TuCasitaTasacionesDesafioTesting.service;

import com.mercadolibre.TuCasitaTasacionesDesafioTesting.dto.request.HouseRequestDto;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.dto.request.RoomRequestDto;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.dto.response.HouseResponseDto;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.dto.response.RoomResponseDto;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.entity.District;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.exceptions.EmptyListException;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.exceptions.InvalidDistrictException;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.exceptions.NullListException;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.repositories.ITuCasitaTasacionesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TuCasitaTasacionesService implements ITuCasitaTasacionesService {

    private static HouseRequestDto savedHouse;
    private static ITuCasitaTasacionesRepository repository;

    public TuCasitaTasacionesService(ITuCasitaTasacionesRepository priceRepository) {
        this.repository = priceRepository;
    }

    /**
     * Save the data entered in the request
     */
    @Override
    public void enterData(HouseRequestDto house) {
        savedHouse = house;
    }

    /**
     * Calculate the total area of the property
     *
     * @return houseResponse
     */
    @Override
    public HouseResponseDto calculateHouseArea() {
        validateNullList();
        validateEmptyList();
        double totalArea = savedHouse.getRooms().stream().mapToDouble(room -> room.getLength() * room.getWidth()).sum();
        HouseResponseDto houseResponse = new HouseResponseDto();
        houseResponse.setProp_name(savedHouse.getProp_name());
        houseResponse.setHouseArea(totalArea);
        return houseResponse;
    }

    /**
     * Calculate which is the biggest room
     *
     * @return roomResponse
     */
    @Override
    public RoomResponseDto calculateBiggestRoom() {
        RoomResponseDto roomResponse = new RoomResponseDto();
        Double maxArea = 0.0, roomArea;
        String biggestRoom = null;
        validateNullList();
        validateEmptyList();

        for (RoomRequestDto room : savedHouse.getRooms()) {
            roomArea = room.getLength() * room.getWidth();
            if (roomArea > maxArea) {
                maxArea = roomArea;
                biggestRoom = room.getEnvironment_name();
            }
        }
        roomResponse.setEnvironment_name(biggestRoom);
        roomResponse.setRoomArea(maxArea);
        return roomResponse;
    }

    /**
     * Calculate the price of the property according to the district
     *
     * @return response
     */
    @Override
    public HouseResponseDto calculatePriceByLocation() {
        enterData(savedHouse);
        HouseResponseDto response = calculateHouseArea();
        District district = repository.findPriceByLocation(savedHouse.getDistrict_name()).orElseThrow(() -> new InvalidDistrictException("El distrito no se encuentra"));
        response.setProp_price(response.getHouseArea() * district.getDistrict_price());
        return response;
    }

    /**
     * Calculate the total area of each room
     *
     * @return houseResponse
     */
    @Override
    public HouseResponseDto calculateRoomArea() {
        HouseResponseDto houseResponse = new HouseResponseDto();
        houseResponse.setRooms(new ArrayList<>());
        validateNullList();
        validateEmptyList();
        savedHouse.getRooms().forEach(room -> {
            RoomResponseDto roomA = new RoomResponseDto();
            roomA.setRoomArea(room.getLength() * room.getWidth());
            roomA.setEnvironment_name(room.getEnvironment_name());
            houseResponse.getRooms().add(roomA);
        });

        houseResponse.setProp_name(savedHouse.getProp_name());
        houseResponse.setDistrict_name(savedHouse.getDistrict_name());
        return houseResponse;
    }

    private void validateEmptyList() {
        if (savedHouse.getRooms().isEmpty()) {
            throw new EmptyListException("La lista se encuentra vacia");
        }
    }

    private void validateNullList() {
        if (savedHouse.getRooms() == null) {
            throw new NullListException("La lista no existe");
        }
    }
}
