package testingchallengue.demo.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import testingchallengue.demo.dtos.*;
import testingchallengue.demo.exceptions.DistrictNotFoundException;
import testingchallengue.demo.exceptions.ExistentDistrictNameException;
import testingchallengue.demo.models.District;
import testingchallengue.demo.models.Estate;
import testingchallengue.demo.repositories.IDistrictRepository;
import testingchallengue.demo.service.EstateService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstateServiceTests {

    @Mock
    IDistrictRepository repository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    EstateService service;

    private ModelMapper localMapper;
    private String districtName;
    private DistrictDTO districtDTO;
    private EstateAssessmentDTO expected;
    private EstateDTO estateDTO;

    @BeforeEach
    public void init() {
        localMapper = new ModelMapper();
        String propName = "House1";
        districtName = "District1";
        String environmentName1 = "bigger";
        String environmentName2 = "medium";
        String environmentName3 = "smaller";
        districtDTO = new DistrictDTO(districtName, 2.0);
        EnvironmentDTO environment1 = new EnvironmentDTO(environmentName1, 2.0, 2.0);
        EnvironmentDTO environment2 = new EnvironmentDTO(environmentName2, 2.0, 1.0);
        EnvironmentDTO environment3 = new EnvironmentDTO(environmentName3, 1.0, 1.0);
        List<EnvironmentDTO> environmentList = new ArrayList();
        environmentList.add(environment1);
        environmentList.add(environment2);
        environmentList.add(environment3);
        EnvironmentDTORes environmentResDto1 = new EnvironmentDTORes(environmentName1, 4.0);
        EnvironmentDTORes environmentResDto2 = new EnvironmentDTORes(environmentName2, 2.0);
        EnvironmentDTORes environmentResDto3 = new EnvironmentDTORes(environmentName3, 1.0);
        List<EnvironmentDTORes> environmentResDtosList = new ArrayList();
        environmentResDtosList.add(environmentResDto1);
        environmentResDtosList.add(environmentResDto2);
        environmentResDtosList.add(environmentResDto3);
        expected = new EstateAssessmentDTO(propName, 7.0, 14, environmentResDto1, environmentResDtosList);
        estateDTO = new EstateDTO(propName, districtName, environmentList);
    }

    @Test
    public void should_calculate_correctly_all_parameters() throws DistrictNotFoundException {
        //Arrange
        District district = localMapper.map(districtDTO, District.class);
        when(modelMapper.map(estateDTO, Estate.class)).thenReturn(localMapper.map(estateDTO, Estate.class));
        when(repository.findDistrictByName(districtName)).thenReturn(district);
        //Act
        EstateAssessmentDTO received = service.getAssessment(estateDTO);
        //Assert
        Assertions.assertEquals(expected, received);
    }

    @Test
    public void should_throes_exception_when_district_name_is_not_on_repository() {
        //Arrange
        when(repository.findDistrictByName(districtName)).thenReturn(null);
        when(modelMapper.map(estateDTO, Estate.class)).thenReturn(localMapper.map(estateDTO, Estate.class));
        //Act

        //Assertion
        Assertions.assertThrows(DistrictNotFoundException.class, () -> service.getAssessment(estateDTO));
    }

    @Test
    public void should_call_repository_passing_district_model_and_return_message()throws ExistentDistrictNameException {
        //Arrange
        District district = localMapper.map(districtDTO, District.class);
        when(modelMapper.map(districtDTO, District.class)).thenReturn(district);
        doNothing().when(repository).addDistrict(district);
        String expected = "District was added Successfully";
        //Act
        String received = service.addDistrict(districtDTO);
        //Assert
        verify(repository, times(1)).addDistrict(district);
        Assertions.assertEquals(expected, received);

    }
}
