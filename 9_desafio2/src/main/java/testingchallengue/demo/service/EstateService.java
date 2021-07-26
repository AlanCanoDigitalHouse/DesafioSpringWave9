package testingchallengue.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testingchallengue.demo.dtos.DistrictDTO;
import testingchallengue.demo.dtos.EnvironmentDTORes;
import testingchallengue.demo.dtos.EstateAssessmentDTO;
import testingchallengue.demo.dtos.EstateDTO;
import testingchallengue.demo.exceptions.DistrictNotFoundException;
import testingchallengue.demo.exceptions.ExistentDistrictNameException;
import testingchallengue.demo.models.District;
import testingchallengue.demo.models.Environment;
import testingchallengue.demo.models.Estate;
import testingchallengue.demo.repositories.IDistrictRepository;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class EstateService implements IEstateService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IDistrictRepository repository;


    @Override
    public EstateAssessmentDTO getAssessment(EstateDTO estateDto) throws DistrictNotFoundException {
        Estate estate = mapper.map(estateDto, Estate.class);

        District district = repository.findDistrictByName(estate.getDistrict_name());

        if (district == null) {
            throw new DistrictNotFoundException(estate.getDistrict_name());
        }

        return new EstateAssessmentDTO(estateDto.getProp_name(),
                calculateEstateSurface(estate.getEnvironments()),
                calculatePrice(estate, district.getPrice()),
                findBiggerEnvironment(estate.getEnvironments()),
                generateEnvironmentsCalculations(estate.getEnvironments()));
    }

    public String addDistrict(DistrictDTO districtDTO) throws ExistentDistrictNameException {
        District district = mapper.map(districtDTO, District.class);
        repository.addDistrict(district);
        return "District was added Successfully";
    }

    private double calculateEnvironmentSurface(Environment environment) {
        return environment.getEnvironment_length() * environment.getEnvironment_width();
    }

    private EnvironmentDTORes generateResponseDto(Environment environment) {
        return new EnvironmentDTORes(environment.getEnvironment_name(), calculateEnvironmentSurface(environment));
    }

    private double calculateEstateSurface(List<Environment> environments) {
        return environments.stream().mapToDouble(e -> calculateEnvironmentSurface(e)).sum();
    }

    private double calculatePrice(Estate estate, Double price) {
        return calculateEstateSurface(estate.getEnvironments()) * price;
    }

    private List<EnvironmentDTORes> generateEnvironmentsCalculations(List<Environment> environments) {
        return environments.stream().map(e -> generateResponseDto(e)).collect(Collectors.toList());
    }

    private EnvironmentDTORes findBiggerEnvironment(List<Environment> environments){
        Environment maxEnvironment;
        try{
            maxEnvironment = environments.stream().max(Comparator.comparing(e -> calculateEnvironmentSurface(e)))
                    .orElseThrow(NoSuchElementException::new);
        } catch(Exception e) {
            return null;
        }
        return generateResponseDto(maxEnvironment);
    }


}
