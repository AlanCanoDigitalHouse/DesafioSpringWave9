package com.mercadolibre.calculadorametroscuadrados.controller;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.PropertyPriceDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.PropertySquareFeetDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.RoomResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CalculateRestController {

    private CalculateService calculateService;

    public CalculateRestController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    @PostMapping("/calculate")
    public HouseResponseDTO calculate(@RequestBody @Valid HouseDTO house) {
        return calculateService.calculate(house);
    }

    @PostMapping("/calculate/property/squareFeet")
    public PropertySquareFeetDTO calculatePropertySquareFeet(@RequestBody @Valid HouseDTO property) {
        return calculateService.calculatePropertySquareFeet(property);
    }

    @PostMapping("/calculate/property/price")
    public PropertyPriceDTO calculatePropertyPrice(@RequestBody @Valid HouseDTO property) {
        return calculateService.calculatePropertyPrice(property);
    }

    @PostMapping("/calculate/property/biggest")
    public RoomResponseDTO calculateBiggestEnvironment(@RequestBody @Valid HouseDTO property) {
        return calculateService.getBiggestEnvironment(property);
    }

    @PostMapping("/calculate/environment")
    public List<RoomResponseDTO> calculateAllEnvironmentsSquareFeet(@RequestBody @Valid HouseDTO property) {
        return calculateService.calculateSquareFeetPerEnvironment(property);
    }
}
