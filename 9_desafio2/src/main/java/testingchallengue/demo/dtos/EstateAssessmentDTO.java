package testingchallengue.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor

public class EstateAssessmentDTO {

    private String name;
    private double totalSquareMeters;
    private double totalPrice;
    private EnvironmentDTORes biggerEnvironment;
    private List<EnvironmentDTORes> environmentsSqrMeters;
}
