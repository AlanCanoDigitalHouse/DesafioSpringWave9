package cl.mercadolibre.desfiotesting.controllers;

import cl.mercadolibre.desfiotesting.DTOs.EnvironmentDTO;
import cl.mercadolibre.desfiotesting.DTOs.EstateDTO;
import cl.mercadolibre.desfiotesting.DTOs.responseDTOs.EnvironmentWithSize;
import cl.mercadolibre.desfiotesting.exceptions.DistrictNotFoundException;
import cl.mercadolibre.desfiotesting.services.IEstateService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.PortUnreachableException;
import java.util.List;

@RestController
@RequestMapping("/estate")
public class EstateController {

    private final IEstateService estateService;

    public EstateController(IEstateService estateService){
        this.estateService = estateService;
    }

    @GetMapping("/calculateTotalSize")
    @ResponseStatus(HttpStatus.OK)
    public Double getTotalSizeEstate(@Valid @RequestBody EstateDTO estateDTO) throws DistrictNotFoundException {
        return this.estateService.calculateEstateSize(estateDTO);
    }

    @GetMapping("/calculatePrice")
    @ResponseStatus(HttpStatus.OK)
    public Double getCalculatePriceEstate(@Valid @RequestBody EstateDTO estateDTO) throws DistrictNotFoundException {
        return this.estateService.calculateEstatePrice(estateDTO);
    }

    @GetMapping("/biggestEnvironment")
    @ResponseStatus(HttpStatus.OK)
    public EnvironmentDTO getBiggestEnvironment(@Valid @RequestBody EstateDTO estateDTO){
        return this.estateService.calculateBiggestEnvironment(estateDTO);
    }

    @GetMapping("/calculateSizeEnvironments")
    @ResponseStatus(HttpStatus.OK)
    public List<EnvironmentWithSize> getSizesEnvironments(@Valid @RequestBody EstateDTO estateDTO){
        return this.estateService.calculateSizeOfEachEnvironment(estateDTO);
    }


}
