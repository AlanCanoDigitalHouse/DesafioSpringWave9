package com.example.tucasita.services.implementations;

import com.example.tucasita.DTO.request.RoomRequestDTO;
import com.example.tucasita.DTO.request.HouseRequestDTO;
import com.example.tucasita.DTO.response.HouseResponseDTO;
import com.example.tucasita.repositories.interfaces.DistrictRepository;
import com.example.tucasita.services.interfaces.HouseService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HouseServiceImpl implements HouseService {

    DistrictRepository districtRepository;

    public HouseServiceImpl(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    public HouseResponseDTO calculateForHouse(HouseRequestDTO houseRequestDTO) {
        List<RoomRequestDTO> rooms = houseRequestDTO.getRooms();
        validateDistrictPrice(houseRequestDTO.getDistrictName(), houseRequestDTO.getDistrictPrice());
        return new HouseResponseDTO(
                getRoomSizes(rooms),
                getMaxSizeRoomName(rooms),
                getHouseValue(rooms, houseRequestDTO.getDistrictPrice()),
                getTotalSquareM(rooms));
    }

    private void validateDistrictPrice(String districtName, Double districtPrice) {
        if (!districtRepository.getPriceOrThrowException(districtName).equals(districtPrice)) {
            throw new IllegalArgumentException("El precio enviado en el JSON es distinto al precio en la base de datos.");
        }
    }

    private Double getTotalSquareM(List<RoomRequestDTO> rooms) {
        return rooms.stream()
                .mapToDouble(this::getSquareM)
                .sum();
    }

    private Double getHouseValue(List<RoomRequestDTO> rooms, Double price) {
        return getTotalSquareM(rooms) * price;
    }

    private String getMaxSizeRoomName(List<RoomRequestDTO> rooms) {
        return rooms.stream()
                .max(Comparator.comparing(this::getSquareM))
                .map(RoomRequestDTO::getName)
                .orElseThrow();
    }

    private Map<String, Double> getRoomSizes(List<RoomRequestDTO> rooms) {
        Map<String, Double> environmentSizes = new HashMap<>();
        rooms.forEach(r -> environmentSizes.put(r.getName(), getSquareM(r)));
        return environmentSizes;
    }

    private Double getSquareM(RoomRequestDTO room) {
        return room.getWidth() * room.getLength();
    }
}
