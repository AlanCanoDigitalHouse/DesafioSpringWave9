package com.desafio.tucasitatasaciones.service;

import com.desafio.tucasitatasaciones.dto.PropertyRequestDTO;
import com.desafio.tucasitatasaciones.dto.PropertyResponseDTO;
import com.desafio.tucasitatasaciones.exception.DistrictNotFoundException;
import com.desafio.tucasitatasaciones.model.District;
import com.desafio.tucasitatasaciones.model.Environment;
import com.desafio.tucasitatasaciones.repository.DistrictRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictService implements IDistrictService{
    private final DistrictRepository districtRepository;

    public DistrictService(DistrictRepository districtRepository){
        this.districtRepository = districtRepository;
    }

    @Override
    public PropertyResponseDTO propertyInfo(PropertyRequestDTO property) throws DistrictNotFoundException{
        double totalArea = calculatePropertyArea(property.getEnvironments());
        double totalPrice = calculatePropertyPrice(property);
        String biggestEnv = findBiggestEnvironment(property.getEnvironments());
        return new PropertyResponseDTO(property, totalArea, totalPrice, biggestEnv);
    }

    private double calculatePropertyPrice(PropertyRequestDTO property) throws DistrictNotFoundException {
        Optional<District> district = districtRepository.findDistrictByName(property.getDistrict_name());
        if(district.isEmpty()){throw new DistrictNotFoundException(property.getDistrict_name());}
        return calculatePropertyArea(property.getEnvironments())*property.getDistrict_price();
    }

    private String findBiggestEnvironment(List<Environment> environments){
        var ref = new Object() {
            double b_env_area = calculateEnviromentArea(environments.get(0));
            String b_env_name = environments.get(0).getEnvironment_name();
        };
        environments.forEach(env -> {
            if(calculateEnviromentArea(env) >= ref.b_env_area){
                ref.b_env_area = calculateEnviromentArea(env);
                ref.b_env_name = env.getEnvironment_name();
            }
        });
        return ref.b_env_name;
    }

    private double calculatePropertyArea(List<Environment> environments){
        double[] acc = {0};
        environments.forEach(env -> acc[0] += calculateEnviromentArea(env));
        return acc[0];
    }

    private double calculateEnviromentArea(Environment enviroment){
        enviroment.setEnvironment_area(enviroment.getEnvironment_length()*enviroment.getEnvironment_width());
        return enviroment.getEnvironment_area();
    }
}
