package com.mercadolibre.tucasita.controller;

import com.mercadolibre.tucasita.domain.House;
import com.mercadolibre.tucasita.dto.RoomDTO;
import com.mercadolibre.tucasita.service.HouseCalculateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/house")
public class CalculateController {

    private HouseCalculateService houseCalculateService;

    public CalculateController(HouseCalculateService houseCalculateService) {
        this.houseCalculateService = houseCalculateService;
    }

    @PostMapping("/totalSquareMeters")
    public Double calculateTotalSquareMeters(@Valid @RequestBody House house) {
        return houseCalculateService.calculateTotalSquareMeters(house);
    }

    @PostMapping("/price")
    public double calculateHousePrice(@Valid @RequestBody House house) {
        return this.houseCalculateService.calculateHousePrice(house);
    }

    @PostMapping("/biggestRoom")
    public RoomDTO findBiggestRoom(@Valid @RequestBody House house) {
        return this.houseCalculateService.findBiggestRoom(house);
    }

    @PostMapping("/roomSizes")
    public List<RoomDTO> calculateRoomSizes(@Valid @RequestBody House house){
        return this.houseCalculateService.calculateRoomSizes(house);
    }

}
