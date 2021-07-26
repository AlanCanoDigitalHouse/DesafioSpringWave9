package cl.mercadolibre.desfiotesting.services;

import cl.mercadolibre.desfiotesting.DTOs.EstateDTO;
import cl.mercadolibre.desfiotesting.DTOs.EnvironmentDTO;
import cl.mercadolibre.desfiotesting.DTOs.responseDTOs.EnvironmentWithSize;
import cl.mercadolibre.desfiotesting.exceptions.DistrictNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstateServiceImpl implements IEstateService {

    private final IDistrictService iDistrictService;

    public EstateServiceImpl(IDistrictService iDistrictService) {
        this.iDistrictService = iDistrictService;
    }

    @Override
    public Double calculateEstateSize(EstateDTO estateDTO) throws DistrictNotFoundException {
        if(this.iDistrictService.existsDistrictByDTO(estateDTO.getDistrictDTO())){
            Double result = 0.0;
            for(EnvironmentDTO hc: estateDTO.getEnvironmentDTOList()) result += hc.getSizeEnvironment();
            return result;
        }else{
            throw new DistrictNotFoundException();
        }
    }

    @Override
    public Double calculateEstatePrice(EstateDTO estateDTO) throws DistrictNotFoundException {
        return this.calculateEstateSize(estateDTO) * estateDTO.getDistrictDTO().getPrice();
    }

    @Override
    public EnvironmentDTO calculateBiggestEnvironment(EstateDTO estateDTO) {
        EnvironmentDTO biggestEnvironment = new EnvironmentDTO();
        biggestEnvironment.setWidth(0.0); biggestEnvironment.setLength(0.0);
        for(EnvironmentDTO hc: estateDTO.getEnvironmentDTOList()){
            if(hc.getSizeEnvironment() > biggestEnvironment.getSizeEnvironment()) biggestEnvironment = hc;
        }
        return biggestEnvironment;
    }

    @Override
    public List<EnvironmentWithSize> calculateSizeOfEachEnvironment(EstateDTO estateDTO) {
        List<EnvironmentWithSize> environments = new ArrayList<>();
        for(EnvironmentDTO hc: estateDTO.getEnvironmentDTOList()){
            EnvironmentWithSize environment = new EnvironmentWithSize();
            environment.setName(hc.getName());
            environment.setSize(hc.getSizeEnvironment());
            environments.add(environment);
        }
        return environments;
    }
}
