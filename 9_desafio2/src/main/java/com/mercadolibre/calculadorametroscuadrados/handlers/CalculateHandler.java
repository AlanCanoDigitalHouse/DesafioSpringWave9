package com.mercadolibre.calculadorametroscuadrados.handlers;

import com.mercadolibre.calculadorametroscuadrados.dto.requests.HouseRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.requests.RoomRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.RoomResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.IncorrectLocationPrice;
import com.mercadolibre.calculadorametroscuadrados.exceptions.LocationNotFound;
import com.mercadolibre.calculadorametroscuadrados.dto.LocationDTO;
import com.mercadolibre.calculadorametroscuadrados.repositories.PriceRepository;
import com.mercadolibre.calculadorametroscuadrados.repositories.PriceRepositoryImpl;

import java.util.ArrayList;

public class CalculateHandler {
    /**
     * Calculates and returns room size.
     * Preconditions: RoomRequestDTO house already validated.
     * Postconditions: returns the room size.
     */
    public static Integer calculateSquareMeters(RoomRequestDTO room){
        return room.getLength()*room.getWidth();
    }

    /**
     * Returns the price of a house's location stored inside the repository.
     * Preconditions: house's LocationDto already validated.
     * Postconditions: returns a HouseResponse or a error in case of invalid input on house location
     */
    public static Integer getLocationPrice(HouseRequestDTO house, PriceRepository priceRepository)  throws LocationNotFound, IncorrectLocationPrice {
        LocationDTO houseLocation = house.getLocation();
        LocationDTO locationDTO = priceRepository.findPriceLocation(houseLocation.getLocation());
        if(!houseLocation.getPrice().equals(locationDTO.getPrice())){
            throw new IncorrectLocationPrice();
        }
        return locationDTO.getPrice();

    }

    /**
     * Creates a list of RoomResponseDto with the information of the RoomRequestDtos
     * Preconditions: house's RoomRequestDtos already validated.
     * Postconditions: Adds the RoomResponseDtos list to the HouseResponseDto
     * with all of its information and returns the house's total size.
     * Also, selects the biggest house of the list and sets it on the response.
     */
    public static Integer setRooms(HouseRequestDTO house, HouseResponseDTO response) {
        Integer totalSquareFeet = 0;
        RoomResponseDTO biggest = null;
        Integer maxRoom = 0;
        ArrayList<RoomResponseDTO> roomResponseDTOS = new ArrayList<>();
        for (RoomRequestDTO room : house.getRooms()) {
            Integer squareFeet = CalculateHandler.calculateSquareMeters(room);
            RoomResponseDTO roomResponseDTO = new RoomResponseDTO(room.getRoomName(),squareFeet);
            roomResponseDTOS.add(roomResponseDTO);
            totalSquareFeet += squareFeet;
            if (biggest == null || squareFeet > maxRoom){
                biggest = roomResponseDTO;
                maxRoom = squareFeet;
            }
        }
        response.setInfoRooms(roomResponseDTOS);
        response.setBiggest(biggest);
        return totalSquareFeet;
    }


    /**
     * Calculates and returns house price.
     * Preconditions: Both values are positive.
     * Postconditions: returns the house price.
     */
    public static Integer calculatePrice(Integer locationPrice, Integer squareMetters) {
        return squareMetters*locationPrice;
    }
}
